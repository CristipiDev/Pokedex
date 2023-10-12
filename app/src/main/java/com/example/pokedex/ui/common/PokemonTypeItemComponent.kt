package com.example.pokedex.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.utils.PokemonTypesEnum

@Composable
fun PokemonTypeItemComponent(
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