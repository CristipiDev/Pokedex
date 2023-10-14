package com.example.pokedex.ui.pokemoninfo

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
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

    var color = R.color.background_blue_steel
    if (state.pokemonTypeEnum.isNotEmpty()) color = state.pokemonTypeEnum[0].color

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
                            style = MaterialTheme.typography.labelSmall
                        )

                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = state.pokemonName.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelLarge
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
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp)) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = color),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        text = Html.fromHtml(state.pokemonDescription,
                            HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth())

                    WeightAndHeightBox(state.pokemonHeight, state.pokemonWeight)

                    Spacer(modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth())

                    Row(modifier = Modifier.fillMaxWidth()
                        .padding(top = 5.dp)) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Species",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.weight(2f),
                            text = "seed",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()
                        .padding(top = 5.dp)) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "Abilities",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.weight(2f),
                            text = "Overgrow, Chlorophyl",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth())
                    BreedingBox(color)

                }
            }

        }
        Box(
            modifier = Modifier
                .padding(20.dp)
                .shadow(10.dp, CircleShape)
                .background(
                    MaterialTheme.colorScheme.background,
                    CircleShape
                )
                .clickable { navController.popBackStack() },
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

@Composable
fun WeightAndHeightBox(
    height: Float,
    weight: Float
) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = "Height",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "$height m",
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = "Weight",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "$weight kg",
                style = MaterialTheme.typography.headlineLarge
            )
        }

    }
}

@Composable
fun BreedingBox(
    color: Int
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Breeding",
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(id = color),
        textAlign = TextAlign.Start
    )

    Row(modifier = Modifier.fillMaxWidth()
        .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Gender rate",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(modifier = Modifier.weight(1f)) {
            Image(
                ImageVector.vectorResource(id = R.drawable.male), "",
                modifier = Modifier.size(15.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.blue))
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "87.5%",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Row(modifier = Modifier.weight(1f)) {
            Image(
                ImageVector.vectorResource(id = R.drawable.female), "",
                modifier = Modifier.size(15.dp),
                colorFilter = ColorFilter.tint(colorResource(R.color.pink))
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "87.5%",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth()
        .padding(top = 5.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Egg group",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(2f),
            text = "monster",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonInfoMail() {
    val navController = NavController(LocalContext.current)
    val pokemonTypeList = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val state = PokemonInfoUiState(1, "pikachu",
        pokemonTypeList, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        "Texto de descripción", 12f, 56f)
    PokemonInfoMain(navController, state)
}