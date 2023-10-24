package com.example.pokedex.domain.model

import com.example.pokedex.ui.utils.PokemonTypesEnum

data class PokemonWithAllModel (
    val pokemon: PokemonModel,
    val typeList: List<TypeModel>,
    var pokemonTypeEnum: List<PokemonTypesEnum>?,
    val abilityList: List<AbilityModel>,
    val eggGroupList: List<EggGroupModel>,
    val statList: List<StatModel>
)