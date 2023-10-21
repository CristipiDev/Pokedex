package com.example.pokedex.domain.model

data class PokemonWithAllModel (
    val pokemon: PokemonModel,
    val typeList: List<TypeModel>,
    val abilityList: List<AbilityModel>
)