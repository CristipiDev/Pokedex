package com.example.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.PokemonEntity
import com.example.pokedex.data.database.entity.PokemonWithTypesEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewPokemon(pokemon: PokemonEntity): Long

    @Query("SELECT * FROM pokemon ORDER BY pokemonId ASC")
    fun getAllPokemon(): List<PokemonWithTypesEntity>

    @Query("SELECT * FROM pokemon WHERE pokemonId = :id")
    fun getPokemonFromId(id: Int): PokemonWithTypesEntity

}