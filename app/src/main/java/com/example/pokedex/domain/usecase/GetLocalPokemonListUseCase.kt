package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.utils.setPokemonTypeEmun
import javax.inject.Inject

class GetLocalPokemonListUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {
    suspend operator fun invoke(): List<PokemonModel> {
        return setPokemonTypeEmun(pokemonsRepo.getLocalPokemonList())
    }
}