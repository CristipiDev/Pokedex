package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.utils.setPokemonTypeEmun
import javax.inject.Inject

class GetLocalPokemonFromIdUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {
    private var pokemonId = -1
    suspend operator fun invoke(): PokemonModel {
        return setPokemonTypeEmun(pokemonsRepo.getLocalPokemonFromId(pokemonId))
    }

    fun setPokemonId(pokemonId: Int) { this.pokemonId = pokemonId }
}