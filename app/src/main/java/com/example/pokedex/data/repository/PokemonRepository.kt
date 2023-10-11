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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PokemonRepository {

    suspend fun getPokemonFromId(pokemonId: Int): PokemonWithTypesModel
    suspend fun getPokemonList(): List<PokemonWithTypesModel>
    suspend fun getLocalPokemonList(): List<PokemonWithTypesModel>

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
            val id = pokemonRequestResponseModel.id
            val name = pokemonRequestResponseModel.name
            val types: ArrayList<TypeModel> = ArrayList()

            val typeIds: ArrayList<Int> = ArrayList()
            pokemonRequestResponseModel.types.forEach {
                //Room insert type
                val typeId = typeDao.insertNewType(TypeEntity(it.type.name))
                typeIds.add(typeId.toInt())

                types.add(TypeModel(typeId.toInt(), it.type.name))
            }
            val img = pokemonRequestResponseModel.sprites.other.officialArtwork.frontDefault

            pokemon = PokemonWithTypesModel(PokemonModel(id, name, null, img), types)

            //Room
            //insert pokemon
             val pokemonId = pokemonDao.insertNewPokemon(PokemonEntity(id, name, img))
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
                val pokemonModel = PokemonModel(
                    pokemon.pokemon.pokemonId,
                    pokemon.pokemon.pokemonName,
                    null,
                    pokemon.pokemon.pokemonImg
                )

                val typeList: ArrayList<TypeModel> = ArrayList()
                pokemon.types.forEach {type ->
                    typeList.add(TypeModel(type.typeId, type.typeName))
                }
                pokemonList.add(PokemonWithTypesModel(pokemonModel, typeList))
            }
        }

        return pokemonList
    }
}