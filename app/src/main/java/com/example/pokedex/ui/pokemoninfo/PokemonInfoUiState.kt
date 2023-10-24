package com.example.pokedex.ui.pokemoninfo

import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.EggGroupModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.StatModel
import com.example.pokedex.ui.utils.PokemonTypesEnum

data class PokemonInfoUiState (
    val pokemon: PokemonModel = PokemonModel(),
    var typeEnum: List<PokemonTypesEnum> = emptyList(),
    val abilityList: List<AbilityModel> = emptyList(),
    val eggGroupList: List<EggGroupModel> = emptyList(),
    val statList: List<StatModel> = emptyList(),
    val statsName: List<String> = emptyList()
    )