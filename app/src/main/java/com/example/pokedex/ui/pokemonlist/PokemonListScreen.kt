package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            PokemonItem(pokemon.pokemonId,
                pokemon.pokemonName,
                pokemon.pokemonType)
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    pokemonType: List<String>
) {
    Row(modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
        Text(
            modifier = Modifier.weight(1f), text = "X")
        Column(
            modifier = Modifier.weight(1f)) {
            Text(modifier = Modifier.fillMaxWidth(), text = id.toString())
            Text(modifier = Modifier.fillMaxWidth(), text = name)
            Row(modifier = Modifier.fillMaxWidth()) {
                pokemonType.forEach {
                    Text(text = it)
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun previewPokemonItem() {
    val list = listOf("Hola", "Adios")
    PokemonItem(1, "Pikachu", list)
}