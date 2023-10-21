package com.example.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["pokemonId", "eggGroupId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["pokemonId"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EggGroupEntity::class,
            parentColumns = ["eggGroupId"],
            childColumns = ["eggGroupId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class PokemonEggGroupCrossResEntity (
    val pokemonId: Int,
    val eggGroupId: Int
)