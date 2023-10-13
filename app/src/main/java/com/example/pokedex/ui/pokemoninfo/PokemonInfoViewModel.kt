package com.example.pokedex.ui.pokemoninfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.usecase.GetLocalPokemonFromIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLocalPokemonFromIdUseCase: GetLocalPokemonFromIdUseCase
): ViewModel() {

    /*private val pokemonId: Int = savedStateHandle["id"]!!
    private val pokemonModel = getPokemonModel(pokemonId)
    var pokemonState by mutableStateOf(PokemonInfoUiState(
        pokemonId = pokemonModel.pokemonId,
        pokemonName = pokemonModel.pokemonName,
        pokemonTypeEnum = pokemonModel.pokemonTypeEnum!!,
        pokemonImg = pokemonModel.pokemonImg
    ))*/

    var pokemonState by mutableStateOf(PokemonInfoUiState())

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocalPokemonFromIdUseCase.setPokemonId(pokemonId)
            val selectedPokemon  = getLocalPokemonFromIdUseCase.invoke()

            pokemonState = pokemonState.copy(
                pokemonId = selectedPokemon.pokemonId,
                pokemonName = selectedPokemon.pokemonName,
                pokemonTypeEnum = selectedPokemon.pokemonTypeEnum!!,
                pokemonImg = selectedPokemon.pokemonImg,
                pokemonDescription = selectedPokemon.pokemonDescription,
                pokemonHeight = selectedPokemon.height,
                pokemonWeight = selectedPokemon.weight
            )
        }
    }
}