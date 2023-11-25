package com.example.pokedex.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithStatsEntity (
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "statId",
        associateBy = Junction(PokemonStatsCrossResEntity::class)
    )
    val stats: List<StatEntity>
)