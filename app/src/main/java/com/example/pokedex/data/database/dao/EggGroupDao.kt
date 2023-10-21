package com.example.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.EggGroupEntity
import com.example.pokedex.data.database.entity.PokemonEggGroupCrossResEntity
import com.example.pokedex.data.database.entity.PokemonWithEggGroupsEntity

@Dao
interface EggGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewEggGroup(newType: EggGroupEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonWithEggGroup(join: PokemonEggGroupCrossResEntity)

    @Query("SELECT * FROM pokemon_egg_group ORDER BY eggGroupId ASC")
    fun getAllEggGroups(): List<EggGroupEntity>

    @Query("SELECT * FROM pokemon WHERE pokemonId = :id")
    fun getEggGroupFromPokemonId(id: Int): PokemonWithEggGroupsEntity

    @Query("SELECT * FROM pokemon_egg_group WHERE eggGroupId = :id")
    fun getEggGroupFromId(id: Int): EggGroupEntity
}