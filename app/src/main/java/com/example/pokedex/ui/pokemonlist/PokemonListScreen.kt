package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokedex.domain.model.PokemonModel


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getPokemon()
    }
    PokemonListContent(pokemonList = viewModel.pokemonListState.pokemonList)

}


@Composable
fun PokemonListContent(
    pokemonList: List<PokemonModel>
) {
    LazyColumn {
        items(pokemonList) {pokemon ->
            PokemonItem(id = pokemon.pokemonId, name = pokemon.pokemonName)
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String
) {
    Row {
        Text(
            modifier = Modifier.weight(1f), text = "X")
        Column(
            modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = id.toString())
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = name)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun previewPokemonItem() {
    PokemonItem(1, "Pikachu")
}