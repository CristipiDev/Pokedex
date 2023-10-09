package com.example.pokedex.domain.model

import com.example.pokedex.ui.utils.PokemonTypesEnum

data class PokemonModel (
    val pokemonId: Int,
    val pokemonName: String,
    val pokemonType: List<String>,
    val pokemonTypeEnum: List<PokemonTypesEnum>?,
    val pokemonImg: String
)