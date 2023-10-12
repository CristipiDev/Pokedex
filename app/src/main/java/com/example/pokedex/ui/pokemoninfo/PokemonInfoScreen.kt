package com.example.pokedex.ui.pokemoninfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.pokedex.R
import com.example.pokedex.ui.common.PokemonTypeItemComponent
import com.example.pokedex.ui.utils.PokemonTypesEnum

@Composable
fun PokemonInfoScreen(
    viewModel: PokemonInfoViewModel = hiltViewModel(),
    navController: NavController,
    pokemonId: Int
) {
    LaunchedEffect(true) {
        viewModel.getPokemon(pokemonId)
    }
    Surface {
        PokemonInfoMain(navController, viewModel.pokemonState)
    }
}

@Composable
fun PokemonInfoMain(
    navController: NavController,
    state: PokemonInfoUiState
) {
    var background = R.color.background_blue_steel
    if (state.pokemonTypeEnum.isNotEmpty()) background = state.pokemonTypeEnum[0].background

    Box (modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(background)
            )) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .offset(y = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(155.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(state.pokemonImg),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .defaultMinSize(minHeight = 1.dp)
                        )
                    }

                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "#${state.pokemonId}",
                            fontFamily = FontFamily.Monospace
                        )

                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = state.pokemonName.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.titleMedium
                        )
                        Row(modifier = Modifier) {
                            if (!state.pokemonTypeEnum.isNullOrEmpty()) {
                                state.pokemonTypeEnum.forEach { type ->
                                    PokemonTypeItemComponent(type)
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .offset(y = 20.dp)
                    .background(
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(30.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "#${state.pokemonId}",
                        fontFamily = FontFamily.Monospace
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        text = "name",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //typeEnum.forEach { type ->
                           // PokemonTypeItem(type)
                         //   Spacer(modifier = Modifier.width(5.dp))
                        //}
                    }

                }
            }

        }
        Box(
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(20.dp)
                .background(
                    MaterialTheme.colorScheme.background,
                    CircleShape
                ),
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Go back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonInfoMail() {
    val navController = NavController(LocalContext.current)
    val pokemonTypeList = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val state = PokemonInfoUiState(1, "pikachu",
        pokemonTypeList, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    PokemonInfoMain(navController, state)
}