package com.example.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.PokemonStatsCrossResEntity
import com.example.pokedex.data.database.entity.PokemonWithStatsEntity
import com.example.pokedex.data.database.entity.StatEntity

@Dao
interface StatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewStat(newType: StatEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonWithStats(join: PokemonStatsCrossResEntity)

    @Query("SELECT * FROM pokemon_stat ORDER BY statId ASC")
    fun getAllStats(): List<StatEntity>

    @Query("SELECT * FROM pokemon WHERE pokemonId = :id")
    fun getStatFromPokemonId(id: Int): PokemonWithStatsEntity

    @Query("SELECT * FROM pokemon_stat WHERE statId = :id")
    fun getStatFromId(id: Int): StatEntity
}