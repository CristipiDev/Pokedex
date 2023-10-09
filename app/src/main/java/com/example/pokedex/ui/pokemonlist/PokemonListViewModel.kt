package com.example.pokedex.ui.pokemonlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.usecase.GetPokemonFromIdUseCase
import com.example.pokedex.domain.usecase.GetPokemonListUseCase
import com.example.pokedex.ui.utils.PokemonTypesEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonFromIdUseCase: GetPokemonFromIdUseCase,
    private val getPokemonListUseCase: GetPokemonListUseCase
): ViewModel() {

    var pokemonListState by mutableStateOf(PokemonListUiState())

    fun getPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonList = getPokemonListUseCase.invoke()

            pokemonListState = pokemonListState.copy(
                pokemonList = pokemonList
            )
        }
    }
}