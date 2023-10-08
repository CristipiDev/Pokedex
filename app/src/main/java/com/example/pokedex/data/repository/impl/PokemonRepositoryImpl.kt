package com.example.pokedex.data.repository.impl

import com.example.pokedex.data.network.PokemonRemoteDataSource
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val dataSource: PokemonRemoteDataSource
): PokemonRepository {
    override suspend fun getPokemonFromId(pokemonId: Int): PokemonModel {
        val pokemon: PokemonModel

        withContext(Dispatchers.IO) {
            val id = dataSource.getPokemonFromId(pokemonId).id
            val name = dataSource.getPokemonFromId(pokemonId).name

            pokemon = PokemonModel(id, name)
        }

        return pokemon
    }

    override suspend fun getPokemonList(): List<PokemonModel> {
        val pokemonList: ArrayList<PokemonModel> = ArrayList()

        withContext(Dispatchers.IO) {
            val pokemonListRequestResponse = dataSource.getPokemonList().results

            pokemonListRequestResponse.forEach {urlResult ->
                val textUrl = urlResult.url.split("/")

                pokemonList.add(getPokemonFromId(textUrl[6].toInt()))
            }
        }

        return pokemonList
    }
}