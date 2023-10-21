package com.example.pokedex.data.repository

import com.example.pokedex.data.database.dao.AbilityDao
import com.example.pokedex.data.database.dao.EggGroupDao
import com.example.pokedex.data.database.dao.PokemonDao
import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.database.entity.AbilityEntity
import com.example.pokedex.data.database.entity.EggGroupEntity
import com.example.pokedex.data.database.entity.PokemonAbilitiesCrossResEntity
import com.example.pokedex.data.database.entity.PokemonEggGroupCrossResEntity
import com.example.pokedex.data.database.entity.PokemonTypesCrossResEntity
import com.example.pokedex.data.database.entity.TypeEntity
import com.example.pokedex.data.network.PokemonRemoteDataSource
import com.example.pokedex.data.network.requesresponse.PokemonAbilitiesRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonEggGroupRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonTypeRequestResponseModel
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.EggGroupModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithAllModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.ui.utils.setListOfAbilityModelFromAbilityEntity
import com.example.pokedex.ui.utils.setListOfEggGroupModelFromEggGroupEntity
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
    private val abilityDao: AbilityDao,
    private val eggGroupDao: EggGroupDao
): PokemonRepository {
    override suspend fun getPokemonFromId(pokemonId: Int): PokemonWithAllModel {
        val pokemon: PokemonWithAllModel

        withContext(Dispatchers.IO) {
            val pokemonRequestResponseModel = dataSource.getPokemonFromId(pokemonId)
            val pokemonSpeciesRequestResponseModel = dataSource.getPokemonSpeciesFromId(pokemonId)
            val pokemonModel = setPokemonModelFromPokemonRequestResponseModel(pokemonRequestResponseModel,
                pokemonSpeciesRequestResponseModel)

            val typesRoom = insertTypeIntoRoom(pokemonRequestResponseModel.types)
            val abilitiesRoom = insertAbilityIntoRoom(pokemonRequestResponseModel.abilities)
            val eggGroupRoom = insertEggGroupIntoRoom(pokemonSpeciesRequestResponseModel.eggGroupList)

            pokemon = PokemonWithAllModel(pokemonModel, typesRoom.first, null, abilitiesRoom.first,
                eggGroupRoom.first)

            insertIntoRoom(pokemonModel, typesRoom.second, abilitiesRoom.second, eggGroupRoom.second)

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
                val eggGroupList = setListOfEggGroupModelFromEggGroupEntity(eggGroupDao.getEggGroupFromPokemonId(pokemon.pokemon.pokemonId).eggGroups)

                pokemonList.add(PokemonWithAllModel(pokemonModel, typeList, null, abilityList,
                    eggGroupList))
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
            val eggGroupList = setListOfEggGroupModelFromEggGroupEntity(eggGroupDao.getEggGroupFromPokemonId(pokemonId).eggGroups)


            pokemon = PokemonWithAllModel(pokemonModel, typeList, null,  abilityList,
                eggGroupList)
        }

        return pokemon
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

    private fun insertEggGroupIntoRoom(eggGroupList: List<PokemonEggGroupRequestResponseModel>)
            : Pair<ArrayList<EggGroupModel>, ArrayList<Int>> {
        val eggGroups: ArrayList<EggGroupModel> = ArrayList()
        val eggGroupIds: ArrayList<Int> = ArrayList()

        eggGroupList.forEach {
            //Room insert type
            var eggGroupId = checkIfEggGroupExists(it.eggGroupName)
            if (eggGroupId == -1) {
                eggGroupId = eggGroupDao.insertNewEggGroup(EggGroupEntity(it.eggGroupName)).toInt()
            }

            eggGroupIds.add(eggGroupId)
            eggGroups.add(EggGroupModel(eggGroupId, it.eggGroupName))
        }
        return Pair(eggGroups, eggGroupIds)
    }

    private fun insertIntoRoom(pokemonModel: PokemonModel, typesRoom: ArrayList<Int>,
                               abilitiesRoom: ArrayList<Int>, eggGroupRoom: ArrayList<Int>) {
        //Room
        //insert pokemon
        val pokemonId = pokemonDao.insertNewPokemon(setPokemonEntityFromPokemonModel(pokemonModel))
        //insert types into this pokemon
        typesRoom.forEach {id ->
            val crossRef = PokemonTypesCrossResEntity(pokemonId.toInt(), id)
            typeDao.insertPokemonWithTypes(crossRef)
        }

        //insert abilities into this pokemon
        abilitiesRoom.forEach {id ->
            val abilityCrossRef = PokemonAbilitiesCrossResEntity(pokemonId.toInt(), id)
            abilityDao.insertPokemonWithAbilities(abilityCrossRef)
        }

        //insert egg groups into this pokemon
        eggGroupRoom.forEach {id ->
            val eggGroupCrossRef = PokemonEggGroupCrossResEntity(pokemonId.toInt(), id)
            eggGroupDao.insertPokemonWithEggGroup(eggGroupCrossRef)
        }

    }

    private fun checkIfTypeExists(typeName: String): Int{
        var typeId = -1
        val typelist = typeDao.getAllTypes()
        typelist.forEach {type ->
            if (type.typeName == typeName) typeId = type.typeId
        }
        return typeId
    }

    private fun checkIfEggGroupExists(eggGroupName: String): Int{
        var id = -1
        val eggGrouplist = eggGroupDao.getAllEggGroups()
        eggGrouplist.forEach {eggGroup ->
            if (eggGroup.eggGroupName == eggGroupName) id = eggGroup.eggGroupId
        }
        return id
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