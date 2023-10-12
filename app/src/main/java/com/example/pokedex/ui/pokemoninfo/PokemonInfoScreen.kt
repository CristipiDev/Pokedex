package com.example.pokedex.ui.pokemoninfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pokedex.R

@Composable
fun PokemonInfoScreen(
    navController: NavController,
    pokemonId: Int
) {
    Surface {
        PokemonInfoMain(navController, pokemonId)
    }
}

@Composable
fun PokemonInfoMain(
    navController: NavController,
    pokemonId: Int
) {
    Box (modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.background_blue_steel)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(155.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .defaultMinSize(minHeight = 1.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxWidth()
            ) {
                Text(text = "#$pokemonId")
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