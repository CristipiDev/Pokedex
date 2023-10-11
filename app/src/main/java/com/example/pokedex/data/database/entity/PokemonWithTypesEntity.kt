package com.example.pokedex.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithTypesEntity (
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "typeId",
        associateBy = Junction(PokemonTypesCrossResEntity::class)
    )
    val types: List<TypeEntity>
)