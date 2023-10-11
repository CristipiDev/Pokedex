package com.example.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.database.entity.PokemonTypesCrossResEntity
import com.example.pokedex.data.database.entity.TypeEntity

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewType(newType: TypeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonWithTypes(join: PokemonTypesCrossResEntity)

    @Query("SELECT * FROM pokemon_type ORDER BY typeId ASC")
    fun getAllTypes(): List<TypeEntity>
}