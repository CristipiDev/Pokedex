package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonSpritesRequestResponseModel (
    @SerializedName("front_default") val frontDefault: String,
    val other: PokemonSpritesOtherRequestResponse
)

data class PokemonSpritesOtherRequestResponse(
    @SerializedName("official-artwork") val officialArtwork: PokemonSpritesOfficialArtworkRequestResponse
)

data class PokemonSpritesOfficialArtworkRequestResponse(
    @SerializedName("front_default") val frontDefault: String
)