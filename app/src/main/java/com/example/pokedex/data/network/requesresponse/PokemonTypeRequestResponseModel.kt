package com.example.pokedex.data.network.requesresponse

data class PokemonTypeRequestResponseModel (
    val type: PokemonTypeSummaryRequestResponse
)

data class PokemonTypeSummaryRequestResponse (
    val name: String,
    val url: String
)