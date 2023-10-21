package com.example.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.AbilityEntity
import com.example.pokedex.data.database.entity.PokemonAbilitiesCrossResEntity
import com.example.pokedex.data.database.entity.PokemonWithAbilitiesEntity

@Dao
interface AbilityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewAbility(newType: AbilityEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonWithAbilities(join: PokemonAbilitiesCrossResEntity)

    @Query("SELECT * FROM pokemon_ability ORDER BY abilityId ASC")
    fun getAllAbilities(): List<AbilityEntity>

    @Query("SELECT * FROM pokemon WHERE pokemonId = :id")
    fun getAbilityFromPokemonId(id: Int): PokemonWithAbilitiesEntity

    @Query("SELECT * FROM pokemon_ability WHERE abilityId = :id")
    fun getAbilityFromId(id: Int): AbilityEntity
}