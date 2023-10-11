package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithTypesModel
import javax.inject.Inject

class GetPokemonFromIdUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {

    private var pokemonId: Int = -1

    suspend operator fun invoke(): PokemonWithTypesModel {
        return pokemonsRepo.getPokemonFromId(pokemonId)
    }

    fun setPokemonId(pokemonId: Int) { this.pokemonId = pokemonId }
}