package com.example.pokedex.data.network.requesresponse

import com.google.gson.annotations.SerializedName

data class PokemonAbilitiesRequestResponseModel (
    @SerializedName("ability") val ability: PokemonAbilityRequestResponse
)

data class PokemonAbilityRequestResponse (
    @SerializedName("name") val abilityName: String
)