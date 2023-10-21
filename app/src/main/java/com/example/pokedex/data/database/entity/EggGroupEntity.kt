package com.example.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_egg_group")
data class EggGroupEntity (
    @ColumnInfo(name = "eggGroupName") val eggGroupName: String,
    @PrimaryKey(autoGenerate = true) var eggGroupId: Int = 0
)