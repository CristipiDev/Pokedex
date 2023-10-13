package com.example.pokedex.ui.pokemoninfo

import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.utils.PokemonTypesEnum

data class PokemonInfoUiState (
    val pokemonId: Int = -1,
    val pokemonName: String = "",
    val pokemonTypeEnum: List<PokemonTypesEnum> = emptyList(),
    val pokemonImg: String = "",
    val pokemonDescription: String = "",
    val pokemonHeight: Float = -1f,
    val pokemonWeight: Float = -1f
)