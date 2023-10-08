package com.example.pokedex.data.network.requesresponse

data class PokemonListRequestResponseModel (
    val results: List<PokemonResultItemRequestResponseModel>
)

data class PokemonResultItemRequestResponseModel (
    val url: String
)