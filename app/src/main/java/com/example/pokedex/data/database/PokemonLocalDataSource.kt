package com.example.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.database.dao.AbilityDao
import com.example.pokedex.data.database.dao.EggGroupDao
import com.example.pokedex.data.database.dao.PokemonDao
import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.database.entity.AbilityEntity
import com.example.pokedex.data.database.entity.EggGroupEntity
import com.example.pokedex.data.database.entity.PokemonAbilitiesCrossResEntity
import com.example.pokedex.data.database.entity.PokemonEggGroupCrossResEntity
import com.example.pokedex.data.database.entity.PokemonEntity
import com.example.pokedex.data.database.entity.PokemonTypesCrossResEntity
import com.example.pokedex.data.database.entity.TypeEntity

@Database(entities = [PokemonEntity::class, TypeEntity::class,
    PokemonTypesCrossResEntity::class, AbilityEntity::class,
    PokemonAbilitiesCrossResEntity::class, EggGroupEntity::class,
    PokemonEggGroupCrossResEntity::class], version = 1)
abstract class PokemonLocalDataSource: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    abstract fun typeDao(): TypeDao

    abstract fun abilityDao(): AbilityDao

    abstract fun eggGroupDao(): EggGroupDao
}