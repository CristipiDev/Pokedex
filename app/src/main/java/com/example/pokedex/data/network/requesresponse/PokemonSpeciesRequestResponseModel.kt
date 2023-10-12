package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesRequestResponseModel (
    @SerializedName("flavor_text_entries") val descriptionList: List<PokemonSpeciesTextRequestResponse>
)

data class PokemonSpeciesTextRequestResponse(
    @SerializedName("flavor_text") val descriptionText: String
)