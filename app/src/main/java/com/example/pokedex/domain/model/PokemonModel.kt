package com.example.pokedex.domain.model

data class PokemonModel (
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonType: List<String>,
    val pokemonImg: String
)