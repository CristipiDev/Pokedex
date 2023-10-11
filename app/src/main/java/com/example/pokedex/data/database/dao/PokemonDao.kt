package com.example.pokedex.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.PokemonEntity

interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon ORDER BY pokemonId DESC")
    fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE pokemonId = :id")
    fun getPokemonFromId(id: Int): PokemonEntity
}