package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonHabitatRequestResponseModel (
    @SerializedName("name") val habitatName: String
)