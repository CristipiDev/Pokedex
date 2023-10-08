package com.example.pokedex.ui.pokemonlist

import com.example.pokedex.domain.model.PokemonModel

data class PokemonListUiState (
    val pokemonList: List<PokemonModel> = emptyList()
)