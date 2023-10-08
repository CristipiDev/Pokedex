package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonSpriteRequestResponseModel (
    @SerializedName("front_default") val frontDefault: String
)