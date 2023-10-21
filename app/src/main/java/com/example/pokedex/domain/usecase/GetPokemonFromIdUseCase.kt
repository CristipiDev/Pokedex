package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonWithAllModel
import javax.inject.Inject

class GetPokemonFromIdUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {

    private var pokemonId: Int = -1

    suspend operator fun invoke(): PokemonWithAllModel {
        return pokemonsRepo.getPokemonFromId(pokemonId)
    }

    fun setPokemonId(pokemonId: Int) { this.pokemonId = pokemonId }
}