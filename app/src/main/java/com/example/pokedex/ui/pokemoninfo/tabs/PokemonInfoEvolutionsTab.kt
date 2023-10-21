package com.example.pokedex.ui.pokemoninfo.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonInfoEvolutionsTab(
    color: Int
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                contentDescription = null,
                modifier = Modifier
                    .width(155.dp)
                    .padding(5.dp)
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(5.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Go back",
                tint = colorResource(id = color)
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                contentDescription = null,
                modifier = Modifier
                    .width(155.dp)
                    .padding(5.dp)
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
        Box(
            modifier = Modifier
                .padding(5.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Go back",
                tint = colorResource(id = color)
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            Image(
                painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                contentDescription = null,
                modifier = Modifier
                    .width(155.dp)
                    .padding(5.dp)
                    .defaultMinSize(minHeight = 1.dp)
            )
        }
    }
}