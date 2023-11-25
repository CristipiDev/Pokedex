package com.example.pokedex.ui.pokemoninfo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.R
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.usecase.GetLocalPokemonFromIdUseCase
import com.example.pokedex.ui.pokemoninfo.tabs.PokemonInfoAboutTab
import com.example.pokedex.ui.utils.PokemonTypesEnum
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

    var pokemonState by mutableStateOf(PokemonInfoUiState(
        statsName = listOf("HP", "ATK", "DEF", "SATK", "SDEF", "SPD")
    ))

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocalPokemonFromIdUseCase.setPokemonId(pokemonId)
            val selectedPokemon  = getLocalPokemonFromIdUseCase.invoke()

            pokemonState = pokemonState.copy(
                pokemon = selectedPokemon.pokemon,
                typeEnum = selectedPokemon.pokemonTypeEnum!!,
                abilityList = selectedPokemon.abilityList,
                eggGroupList = selectedPokemon.eggGroupList,
                statList = selectedPokemon.statList
            )
        }
    }
}