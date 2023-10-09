package com.example.pokedex.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import com.example.pokedex.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .height(IntrinsicSize.Min)) {
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
                    fontFamily = FontFamily.Monospace
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    text = name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    typeEnum.forEach { type ->
                        PokemonTypeItem(type)
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
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = typeEnum.name,
                modifier = Modifier.padding(horizontal = 3.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonItem() {
    val list = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
    PokemonItem(1, "pikachu", list, img)
}