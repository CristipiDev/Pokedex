package com.example.pokedex.data.repository

import com.example.pokedex.data.database.dao.AbilityDao
import com.example.pokedex.data.database.dao.PokemonDao
import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.database.entity.AbilityEntity
import com.example.pokedex.data.database.entity.PokemonAbilitiesCrossResEntity
import com.example.pokedex.data.database.entity.PokemonTypesCrossResEntity
import com.example.pokedex.data.database.entity.TypeEntity
import com.example.pokedex.data.network.PokemonRemoteDataSource
import com.example.pokedex.data.network.requesresponse.PokemonAbilitiesRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonTypeRequestResponseModel
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.PokemonWithAllModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.ui.utils.setListOfAbilityModelFromAbilityEntity
import com.example.pokedex.ui.utils.setListOfTypeModelFromTypeEntity
import com.example.pokedex.ui.utils.setPokemonEntityFromPokemonModel
import com.example.pokedex.ui.utils.setPokemonModelFromPokemonEntity
import com.example.pokedex.ui.utils.setPokemonModelFromPokemonRequestResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PokemonRepository {

    suspend fun getPokemonFromId(pokemonId: Int): PokemonWithAllModel
    suspend fun getPokemonList(): List<PokemonWithAllModel>
    suspend fun getLocalPokemonList(): List<PokemonWithAllModel>
    suspend fun getLocalPokemonFromId(pokemonId: Int): PokemonWithAllModel
}

class PokemonRepositoryImpl @Inject constructor(
    private val dataSource: PokemonRemoteDataSource,
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
    private val abilityDao: AbilityDao
): PokemonRepository {
    override suspend fun getPokemonFromId(pokemonId: Int): PokemonWithAllModel {
        val pokemon: PokemonWithAllModel

        withContext(Dispatchers.IO) {
            val pokemonRequestResponseModel = dataSource.getPokemonFromId(pokemonId)
            val pokemonModel = setPokemonModelFromPokemonRequestResponseModel(pokemonRequestResponseModel,
                dataSource.getPokemonSpeciesFromId(pokemonId))

            val typesRoom = insertTypeIntoRoom(pokemonRequestResponseModel.types)
            val abilitiesRoom = insertAbilityIntoRoom(pokemonRequestResponseModel.abilities)

            pokemon = PokemonWithAllModel(pokemonModel, typesRoom.first, abilitiesRoom.first)

            //Room
            //insert pokemon
             val pokemonId = pokemonDao.insertNewPokemon(setPokemonEntityFromPokemonModel(pokemonModel))
            //insert types into this pokemon
            typesRoom.second.forEach {id ->
                val crossRef = PokemonTypesCrossResEntity(pokemonId.toInt(), id)
                typeDao.insertPokemonWithTypes(crossRef)
            }

            //insert abilities into this pokemon
            abilitiesRoom.second.forEach {id ->
                val abilityCrossRef = PokemonAbilitiesCrossResEntity(pokemonId.toInt(), id)
                abilityDao.insertPokemonWithAbilities(abilityCrossRef)
            }
        }

        return pokemon
    }

    override suspend fun getPokemonList(): List<PokemonWithAllModel> {
        val pokemonList: ArrayList<PokemonWithAllModel> = ArrayList()

        withContext(Dispatchers.IO) {
            val pokemonListRequestResponse = dataSource.getPokemonList().results

            pokemonListRequestResponse.forEach {urlResult ->
                val textUrl = urlResult.url.split("/")

                pokemonList.add(getPokemonFromId(textUrl[6].toInt()))
            }
        }

        return pokemonList
    }

    override suspend fun getLocalPokemonList(): List<PokemonWithAllModel> {
        val pokemonList: ArrayList<PokemonWithAllModel> = ArrayList()

        withContext(Dispatchers.IO) {
            pokemonDao.getAllPokemon().forEach {pokemon ->
                val pokemonModel = setPokemonModelFromPokemonEntity(pokemon.pokemon)

                val typeList = setListOfTypeModelFromTypeEntity(pokemon.types)
                val abilityList = setListOfAbilityModelFromAbilityEntity(abilityDao.getAbilityFromPokemonId(pokemon.pokemon.pokemonId).abilities)

                pokemonList.add(PokemonWithAllModel(pokemonModel, typeList, abilityList))
            }
        }

        return pokemonList
    }

    override suspend fun getLocalPokemonFromId(pokemonId: Int): PokemonWithAllModel {
        val pokemon: PokemonWithAllModel

        withContext(Dispatchers.IO) {
            val pokemonEntity = pokemonDao.getPokemonFromId(pokemonId)
            val pokemonModel = setPokemonModelFromPokemonEntity(pokemonEntity.pokemon)

            val typeList = setListOfTypeModelFromTypeEntity(pokemonEntity.types)
            val abilityList = setListOfAbilityModelFromAbilityEntity(abilityDao.getAbilityFromPokemonId(pokemonId).abilities)

            pokemon = PokemonWithAllModel(pokemonModel, typeList, abilityList)
        }

        return pokemon
    }

    private fun checkIfTypeExists(typeName: String): Int{
        var typeId = -1
        val typelist = typeDao.getAllTypes()
        typelist.forEach {type ->
            if (type.typeName == typeName) typeId = type.typeId
        }
        return typeId
    }

    private fun insertTypeIntoRoom(pokemonType: List<PokemonTypeRequestResponseModel>)
    : Pair<ArrayList<TypeModel>, ArrayList<Int>> {
        val types: ArrayList<TypeModel> = ArrayList()
        val typeIds: ArrayList<Int> = ArrayList()

        pokemonType.forEach {
            //Room insert type
            var typeId = checkIfTypeExists(it.type.name)
            if (typeId == -1) {
                typeId = typeDao.insertNewType(TypeEntity(it.type.name)).toInt()
            }

            typeIds.add(typeId)
            types.add(TypeModel(typeId, it.type.name))
        }
        return Pair(types, typeIds)
    }


    private fun insertAbilityIntoRoom(pokemonAbilities: List<PokemonAbilitiesRequestResponseModel>)
            : Pair<ArrayList<AbilityModel>, ArrayList<Int>> {
        val abilities: ArrayList<AbilityModel> = ArrayList()
        val abilitiesIds: ArrayList<Int> = ArrayList()

        pokemonAbilities.forEach {
            //Room insert type
            var abilityId = checkIfAbilityExists(it.ability.abilityName)
            if (abilityId == -1) {
                abilityId = abilityDao.insertNewAbility(AbilityEntity(it.ability.abilityName)).toInt()
            }

            abilitiesIds.add(abilityId)
            abilities.add(AbilityModel(abilityId, it.ability.abilityName))
        }
        return Pair(abilities, abilitiesIds)
    }

    private fun checkIfAbilityExists(abilityName: String): Int{
        var abilityId = -1
        val abilitylist = abilityDao.getAllAbilities()
        abilitylist.forEach {ability ->
            if (ability.abilityName == abilityName) abilityId = ability.abilityId
        }
        return abilityId
    }
}