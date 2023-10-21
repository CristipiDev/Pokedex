package com.example.pokedex.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["pokemonId", "abilityId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["pokemonId"],
            childColumns = ["pokemonId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AbilityEntity::class,
            parentColumns = ["abilityId"],
            childColumns = ["abilityId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class PokemonAbilitiesCrossResEntity (
    val pokemonId: Int,
    val abilityId: Int
)