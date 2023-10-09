package com.example.pokedex.data.repository

import com.example.pokedex.domain.model.PokemonModel

interface PokemonRepository {

    suspend fun getPokemonFromId(pokemonId: Int): PokemonModel
    suspend fun getPokemonList(): List<PokemonModel>

}