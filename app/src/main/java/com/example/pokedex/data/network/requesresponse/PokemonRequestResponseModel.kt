package com.example.pokedex.data.network.requesresponse

data class PokemonRequestResponseModel (
    val id: Int,
    val name: String,
    val types: List<PokemonTypeRequestResponseModel>
)