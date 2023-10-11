package com.example.pokedex.domain.model

data class PokemonWithTypesModel (
    val pokemon: PokemonModel,
    val typeList: List<TypeModel>
)