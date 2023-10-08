package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Preview(showBackground = true)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getPokemon()
    }

    PokemonListContent(viewModel.pokemonListState.pokemonId,
        viewModel.pokemonListState.pokemonName)
}


@Composable
fun PokemonListContent(
    id: Int,
    name: String
) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f), text = "Id:")
            Text(
                modifier = Modifier.weight(1f),text = id.toString())
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.weight(1f), text = "Name:")
            Text(
                modifier = Modifier.weight(1f),text = name)
        }
    }
}