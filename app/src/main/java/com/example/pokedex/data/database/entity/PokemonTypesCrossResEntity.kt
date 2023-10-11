package com.example.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(primaryKeys = ["pokemonId", "typeId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["pokemonId"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TypeEntity::class,
            parentColumns = ["typeId"],
            childColumns = ["typeId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class PokemonTypesCrossResEntity (
    val pokemonId: Int,
    val typeId: Int
)