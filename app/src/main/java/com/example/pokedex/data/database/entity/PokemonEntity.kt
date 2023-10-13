package com.example.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity (
    @PrimaryKey val pokemonId: Int,
    @ColumnInfo(name = "pokemonName") val pokemonName: String,
    @ColumnInfo(name = "pokemonImg") val pokemonImg: String,
    @ColumnInfo(name = "pokemonDescription") val pokemonDescription: String,
    @ColumnInfo(name = "pokemonHeight") val pokemonHeight: Float,
    @ColumnInfo(name = "pokemonWeight") val pokemonWeight: Float
)