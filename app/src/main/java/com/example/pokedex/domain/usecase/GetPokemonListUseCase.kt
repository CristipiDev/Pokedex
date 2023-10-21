package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.utils.setPokemonListTypeEmun
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {
    suspend operator fun invoke(): List<PokemonModel> {
        return setPokemonListTypeEmun(pokemonsRepo.getPokemonList())
    }

}