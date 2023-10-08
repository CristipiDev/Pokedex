package com.example.pokedex.domain.usecase

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.domain.model.PokemonModel
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val pokemonsRepo: PokemonRepository
) {
    suspend operator fun invoke(): List<PokemonModel> {
        return pokemonsRepo.getPokemonList()
    }

}