package com.example.pokedex.data.network

import com.example.pokedex.data.network.requesresponse.PokemonListRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonRequestResponseModel
import com.example.pokedex.data.network.requesresponse.PokemonResultItemRequestResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRemoteDataSource {

    @GET("pokemon/" + "{pokemonId}")
    suspend fun getPokemonFromId(@Path("pokemonId") pokemonId: Int): PokemonRequestResponseModel

    @GET("pokemon/?limit=20&offset=0")
    suspend fun getPokemonList(): PokemonListRequestResponseModel
}