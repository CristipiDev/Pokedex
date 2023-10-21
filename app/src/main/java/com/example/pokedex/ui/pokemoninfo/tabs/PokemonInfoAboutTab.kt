package com.example.pokedex.ui.pokemoninfo.tabs

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import com.example.pokedex.R
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.pokemoninfo.PokemonInfoUiState
import com.example.pokedex.ui.utils.PokemonTypesEnum

@Composable
fun PokemonInfoAboutTab(
    color: Int,
    state: PokemonInfoUiState
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(horizontal = 20.dp, vertical = 30.dp)) {

        item {
            DescriptionBox(color, state.pokemon.pokemonDescription)
            Spacer(modifier = Modifier
                .height(30.dp)
                .fillMaxWidth())
        }


        item {
            WeightAndHeightBox(state.pokemon.height, state.pokemon.weight)

            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Species",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier.weight(2f),
                    text = state.pokemon.specie,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Abilities",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (modifier = Modifier.weight(2f)) {
                    state.abilityList.forEachIndexed { index, ability  ->
                        var abilityText = ability.abilityName
                        if (index != state.abilityList.size-1) abilityText = "${ability.abilityName}, "
                        Text(
                            text = abilityText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

            Spacer(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            )
        }

        item {
            CaptureInfoBox(color, state.pokemon.captureRate,
                state.pokemon.habitat)
            Spacer(modifier = Modifier
                .height(20.dp)
                .fillMaxWidth())
        }

        item {
            BreedingBox(color, state.pokemon.femaleRate)
        }

    }
}

@Composable
fun DescriptionBox(
    color: Int,
    description: String
) {
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
        text = Html.fromHtml(description,
            HtmlCompat.FROM_HTML_MODE_LEGACY).toString(),
        style = MaterialTheme.typography.bodyMedium
    )
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
fun CaptureInfoBox(
    color: Int,
    captureRate: Int,
    habitat: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Capture info",
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(id = color),
        textAlign = TextAlign.Start
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Capture rate",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(2f),
            text = "$captureRate%",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Habitat",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(2f),
            text = habitat,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BreedingBox(
    color: Int,
    femaleRate: Float
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Breeding",
        style = MaterialTheme.typography.titleMedium,
        color = colorResource(id = color),
        textAlign = TextAlign.Start
    )

    Row(modifier = Modifier
        .fillMaxWidth()
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
            val maleRate = 100-femaleRate
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "$maleRate%",
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
                text = "$femaleRate%",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
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
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 5.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Growth rate",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.weight(2f),
            text = "12%",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewAboutScreen() {
    val pokemonModel = PokemonModel(1, "bulbasur",
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png",
        "A strange seed was\\nplanted on its\\nback at birth.\\fThe plant sprouts\\nand grows with\\nthis POKéMON.",
        12f, 50f, "species", 45,
        "grassland", 30f)
    val pokemonTypeList = listOf(PokemonTypesEnum.GRASS, PokemonTypesEnum.POISON)
    val abilityList = listOf(AbilityModel(1, "grass"))
    val state = PokemonInfoUiState(pokemonModel, pokemonTypeList, abilityList)

    PokemonInfoAboutTab(R.color.background_blue_water, state)
}