package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import com.example.pokedex.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.utils.PokemonTypesEnum


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
                pokemon.pokemonTypeEnum!!,
                pokemon.pokemonImg)
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    typeEnum: List<PokemonTypesEnum>,
    img: String
) {
    Row(modifier = Modifier
        .padding(vertical = 5.dp, horizontal = 10.dp)
        .background(
            colorResource(id = typeEnum[0].background),
            RoundedCornerShape(size = 5.dp)
        ),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberAsyncImagePainter(img),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
                .padding(horizontal = 10.dp)
        )
        Column(
            modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)) {
            Text(modifier = Modifier.fillMaxWidth(),
                text = id.toString(),
                style = MaterialTheme.typography.titleSmall)
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), text = name,
                style = MaterialTheme.typography.titleMedium)
            Row(modifier = Modifier.fillMaxWidth()) {
                typeEnum.forEach {type ->
                    PokemonTypeItem(type)
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }

        }
    }

}

@Composable
fun PokemonTypeItem(
    typeEnum: PokemonTypesEnum
) {
    Box(modifier = Modifier.shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))) {
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(typeEnum.color),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                ImageVector.vectorResource(id = typeEnum.icon), "",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = typeEnum.name, color = colorResource(id = R.color.white),
                modifier = Modifier.padding(horizontal = 3.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonItem() {
    val list = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
    PokemonItem(1, "Pikachu", list, img)
}