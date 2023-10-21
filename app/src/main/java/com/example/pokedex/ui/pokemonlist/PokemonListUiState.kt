package com.example.pokedex.ui.pokemonlist

import com.example.pokedex.domain.model.PokemonWithAllModel

data class PokemonListUiState (
    val pokemonList: List<PokemonWithAllModel> = emptyList()
)