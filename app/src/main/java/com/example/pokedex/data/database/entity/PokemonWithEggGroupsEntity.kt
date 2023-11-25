package com.example.pokedex.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithEggGroupsEntity (
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "eggGroupId",
        associateBy = Junction(PokemonEggGroupCrossResEntity::class)
    )
    val eggGroups: List<EggGroupEntity>
)