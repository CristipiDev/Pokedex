package com.example.pokedex.data.network.requesresponse

data class PokemonRequestResponseModel (
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeRequestResponseModel>,
    val sprites: PokemonSpritesRequestResponseModel,
    val abilities: List<PokemonAbilitiesRequestResponseModel>,
    val stats: List<PokemonStatsRequestResponseModel>
)