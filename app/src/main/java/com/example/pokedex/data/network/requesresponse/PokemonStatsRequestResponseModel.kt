package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonStatsRequestResponseModel(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: PokemonStatRequestResponseModel
)

data class PokemonStatRequestResponseModel(
    @SerializedName("name") val nameStat: String
)