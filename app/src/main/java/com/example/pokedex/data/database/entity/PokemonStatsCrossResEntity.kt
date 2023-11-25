package com.example.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["pokemonId", "statId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["pokemonId"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StatEntity::class,
            parentColumns = ["statId"],
            childColumns = ["statId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class PokemonStatsCrossResEntity (
    val pokemonId: Int,
    val statId: Int
)