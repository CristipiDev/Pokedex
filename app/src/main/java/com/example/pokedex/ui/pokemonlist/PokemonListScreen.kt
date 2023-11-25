package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.PokemonWithAllModel
import com.example.pokedex.ui.common.PokemonTypeItemComponent
import com.example.pokedex.ui.navigation.AppRoutes
import com.example.pokedex.ui.utils.PokemonTypesEnum


@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(true) {
        viewModel.getPokemonList()
    }
    PokemonListContent(
        pokemonList = viewModel.pokemonListState.pokemonList,
        navController = navController)

}


@Composable
fun PokemonListContent(
    pokemonList: List<PokemonWithAllModel>,
    navController: NavController
) {
    LazyColumn {
        items(pokemonList) {pokemon ->
            PokemonItem(pokemon.pokemon.pokemonId,
                pokemon.pokemon.pokemonName,
                pokemon.pokemonTypeEnum!!,
                pokemon.pokemon.pokemonImg,
                navController)
        }
    }
}

@Composable
fun PokemonItem(
    id: Int,
    name: String,
    typeEnum: List<PokemonTypesEnum>,
    img: String,
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .height(IntrinsicSize.Min)
        .clickable { navController.navigate(
            AppRoutes.PokemonInfoScreen.route + "/$id") }) {
        Row(
            modifier = Modifier
                .padding(top = 25.dp)
                .background(
                    colorResource(id = typeEnum[0].background),
                    RoundedCornerShape(size = 5.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.padding(
                    start = 155.dp,
                    top = 10.dp, bottom = 10.dp
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "#$id",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.labelLarge
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    typeEnum.forEach { type ->
                        PokemonTypeItemComponent(type)
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .width(155.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonItem() {
    val list = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    val navController = NavController(LocalContext.current)

    PokemonItem(1, "pikachu", list, img, navController)
}