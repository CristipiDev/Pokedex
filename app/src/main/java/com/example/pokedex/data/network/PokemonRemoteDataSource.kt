package com.example.pokedex.data.network

import com.example.pokedex.data.network.requesresponse.PokemonRequestResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRemoteDataSource {

    @GET("pokemon/" + "{pokemonId}")
    suspend fun getPokemonFromId(@Path("pokemonId") pokemonId: Int): PokemonRequestResponseModel
}