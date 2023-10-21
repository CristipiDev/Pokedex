package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesRequestResponseModel (
    @SerializedName("flavor_text_entries") val descriptionList: List<PokemonSpeciesTextRequestResponse>,
    @SerializedName("genera") val speciesList: List<PokemonSpeciesNameRequestResponse>,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("habitat") val habitat: PokemonHabitatRequestResponseModel,
    @SerializedName("gender_rate") val femaleRate: Float
)

data class PokemonSpeciesTextRequestResponse(
    @SerializedName("flavor_text") val descriptionText: String
)

data class PokemonSpeciesNameRequestResponse(
    @SerializedName("genus") val specieName: String,
    @SerializedName("language") val specieLanguage: PokemonSpeciesNameLanguageRequestResponse
)

data class PokemonSpeciesNameLanguageRequestResponse(
    @SerializedName("name") val languageName: String
)
