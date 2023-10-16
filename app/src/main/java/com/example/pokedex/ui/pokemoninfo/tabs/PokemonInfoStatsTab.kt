package com.example.pokedex.ui.pokemoninfo.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.R

@Composable
fun PokemonInfoStatsTab(
    color: Int,
    background: Int
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 20.dp, vertical = 30.dp)) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Stats",
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = color),
            textAlign = TextAlign.Start
        )

        Column(modifier = Modifier.padding(10.dp)) {
            StatRow(color, background)
            StatRow(color, background)
            StatRow(color, background)
            StatRow(color, background)
            StatRow(color, background)
        }

        Spacer(
            modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
        )

        MovesTable(color)


    }
}

@Composable
fun StatRow(
    color: Int,
    background: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 5.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "hp",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = color)
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "44",
            style = MaterialTheme.typography.bodyMedium
        )

        LinearProgressIndicator(
            progress = 0.44f,
            color = colorResource(color),
            modifier = Modifier
                .height(10.dp)
                .clip(RoundedCornerShape(16.dp)),
            trackColor = colorResource(background)
        )
    }
}

@Composable
fun MovesTable(
    color: Int
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)) {
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "Move",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Level learned at",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
        Divider(Modifier.padding(vertical = 5.dp),
            1.dp, colorResource(color))
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "mega-punch",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = color)
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "0",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun previewPokemonInfoStatsTab() {
    PokemonInfoStatsTab(R.color.blue_dragon, R.color.background_blue_dragon)
}