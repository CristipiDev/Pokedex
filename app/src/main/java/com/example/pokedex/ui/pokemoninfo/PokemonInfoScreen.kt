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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.R
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.common.PokemonTypeItemComponent
import com.example.pokedex.ui.pokemoninfo.tabs.PokemonInfoAboutTab
import com.example.pokedex.ui.pokemoninfo.tabs.PokemonInfoEvolutionsTab
import com.example.pokedex.ui.pokemoninfo.tabs.PokemonInfoStatsTab
import com.example.pokedex.ui.utils.PokemonTypesEnum
import com.google.accompanist.pager.ExperimentalPagerApi

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
    if (state.typeEnum.isNotEmpty()) background = state.typeEnum[0].background

    var color = R.color.background_blue_steel
    if (state.typeEnum.isNotEmpty()) color = state.typeEnum[0].color

    val tabs = mutableListOf<Pair<String, @Composable () -> Unit>>()
    tabs.add(Pair("About") { PokemonInfoAboutTab(color, state) })
    tabs.add(Pair("Stats") { PokemonInfoStatsTab(color, background, state.statList, state.statsName) })
    //tabs.add(Pair("Evolutions") { PokemonInfoEvolutionsTab(color) })

    var stateIndex by remember { mutableStateOf(0) }



    Box (modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(background)
            )
            .padding(top = 20.dp)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(155.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(state.pokemon.pokemonImg),
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
                            text = "#${state.pokemon.pokemonId}",
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            text = state.pokemon.pokemonName.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.labelLarge
                        )
                        Row(modifier = Modifier) {
                            if (!state.typeEnum.isNullOrEmpty()) {
                                state.typeEnum.forEach { type ->
                                    PokemonTypeItemComponent(type)
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            }
                        }
                        Text(modifier = Modifier
                            .padding(top = 8.dp),
                            text = state.pokemon.generation.uppercase(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(4f)
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                TabRow(
                    selectedTabIndex = stateIndex,
                    contentColor = colorResource(id = color),
                    containerColor = colorResource(R.color.transparent)
                ) {
                    tabs.forEachIndexed {index, tab ->

                        var tabBackground = background
                        var tabTextColor = R.color.white
                        if (stateIndex == index) {
                            tabBackground = R.color.white
                            tabTextColor = color
                        }
                        Tab(
                            selected = stateIndex == index,
                            onClick = {
                                stateIndex = index
                                //onTabIndexChanged(index)
                            },
                            selectedContentColor = colorResource(background),
                            unselectedContentColor = MaterialTheme.colorScheme.background,
                            modifier = Modifier
                                .background(
                                    colorResource(tabBackground),
                                    RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                                ),
                            text = {
                                Text(
                                    tab.first,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = colorResource(id = tabTextColor)
                                )
                            }
                        )
                    }
                }

                tabs[stateIndex].second()
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



@Preview(showBackground = true)
@Composable
fun PreviewPokemonInfoMail() {
    val navController = NavController(LocalContext.current)
    val pokemonTypeList = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val abilityList = listOf(AbilityModel(1, "grass"))

    val pokemonModel = PokemonModel(1, "pikachu",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
        "Texto de descripción", 12f, 56f, generation = "generation-i")

    val state = PokemonInfoUiState(pokemonModel, pokemonTypeList, abilityList)

    PokemonInfoMain(navController, state)
}