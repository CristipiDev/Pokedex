package com.example.pokedex.domain.model

import com.example.pokedex.ui.utils.PokemonTypesEnum

data class PokemonModel (
    var pokemonId: Int = -1,
    val pokemonName: String = "",
    val pokemonImg: String = "",
    val pokemonDescription: String = "",
    val height: Float = 0f,
    val weight: Float = 0f,
    val specie: String = "",
    var captureRate: Int = -1,
    var habitat: String = ""
)