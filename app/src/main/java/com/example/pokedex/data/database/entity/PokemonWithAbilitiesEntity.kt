package com.example.pokedex.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PokemonWithAbilitiesEntity (
    @Embedded val pokemon: PokemonEntity,
    @Relation(
        parentColumn = "pokemonId",
        entityColumn = "abilityId",
        associateBy = Junction(PokemonAbilitiesCrossResEntity::class)
    )
    val abilities: List<AbilityEntity>
)