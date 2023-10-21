package com.example.pokedex.data.repository

import com.example.pokedex.data.database.dao.PokemonDao
import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.database.entity.PokemonEntity
import com.example.pokedex.data.database.entity.PokemonTypesCrossResEntity
import com.example.pokedex.data.database.entity.TypeEntity
import com.example.pokedex.data.network.PokemonRemoteDataSource
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithTypesModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.ui.utils.setPokemonEntityFromPokemonModel
import com.example.pokedex.ui.utils.setPokemonModelFromPokemonEntity
import com.example.pokedex.ui.utils.setPokemonModelFromPokemonRequestResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PokemonRepository {

    suspend fun getPokemonFromId(pokemonId: Int): PokemonWithTypesModel
    suspend fun getPokemonList(): List<PokemonWithTypesModel>
    suspend fun getLocalPokemonList(): List<PokemonWithTypesModel>
    suspend fun getLocalPokemonFromId(pokemonId: Int): PokemonWithTypesModel
}

class PokemonRepositoryImpl @Inject constructor(
    private val dataSource: PokemonRemoteDataSource,
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao
): PokemonRepository {
    override suspend fun getPokemonFromId(pokemonId: Int): PokemonWithTypesModel {
        val pokemon: PokemonWithTypesModel

        withContext(Dispatchers.IO) {
            val pokemonRequestResponseModel = dataSource.getPokemonFromId(pokemonId)
            val pokemonModel = setPokemonModelFromPokemonRequestResponseModel(pokemonRequestResponseModel,
                dataSource.getPokemonSpeciesFromId(pokemonId))

            val types: ArrayList<TypeModel> = ArrayList()
            val typeIds: ArrayList<Int> = ArrayList()
            pokemonRequestResponseModel.types.forEach {
                //Room insert type
                var typeId = checkIfTypeExists(it.type.name)
                if (typeId == -1) {
                    typeId = typeDao.insertNewType(TypeEntity(it.type.name)).toInt()
                }

                typeIds.add(typeId)
                types.add(TypeModel(typeId, it.type.name))
            }

            pokemon = PokemonWithTypesModel(pokemonModel, types)

            //Room
            //insert pokemon
             val pokemonId = pokemonDao.insertNewPokemon(setPokemonEntityFromPokemonModel(pokemonModel))
            //insert types into this pokemon
            typeIds.forEach {id ->
                val crossRef = PokemonTypesCrossResEntity(pokemonId.toInt(), id)
                typeDao.insertPokemonWithTypes(crossRef)
            }
        }

        return pokemon
    }

    override suspend fun getPokemonList(): List<PokemonWithTypesModel> {
        val pokemonList: ArrayList<PokemonWithTypesModel> = ArrayList()

        withContext(Dispatchers.IO) {
            val pokemonListRequestResponse = dataSource.getPokemonList().results

            pokemonListRequestResponse.forEach {urlResult ->
                val textUrl = urlResult.url.split("/")

                pokemonList.add(getPokemonFromId(textUrl[6].toInt()))
            }
        }

        return pokemonList
    }

    override suspend fun getLocalPokemonList(): List<PokemonWithTypesModel> {
        val pokemonList: ArrayList<PokemonWithTypesModel> = ArrayList()

        withContext(Dispatchers.IO) {
            pokemonDao.getAllPokemon().forEach {pokemon ->
                val pokemonModel = setPokemonModelFromPokemonEntity(pokemon.pokemon)

                val typeList: ArrayList<TypeModel> = ArrayList()
                pokemon.types.forEach {type ->
                    typeList.add(TypeModel(type.typeId, type.typeName))
                }
                pokemonList.add(PokemonWithTypesModel(pokemonModel, typeList))
            }
        }

        return pokemonList
    }

    override suspend fun getLocalPokemonFromId(pokemonId: Int): PokemonWithTypesModel {
        val pokemon: PokemonWithTypesModel

        withContext(Dispatchers.IO) {
            val pokemonEntity = pokemonDao.getPokemonFromId(pokemonId)
            val pokemonModel = setPokemonModelFromPokemonEntity(pokemonEntity.pokemon)

            val typeList: ArrayList<TypeModel> = ArrayList()
            pokemonEntity.types.forEach {type ->
                typeList.add(TypeModel(type.typeId, type.typeName))
            }

            pokemon = PokemonWithTypesModel(pokemonModel, typeList)
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
}