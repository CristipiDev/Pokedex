package com.example.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "pokemon_stat")
data class StatEntity (
    @ColumnInfo(name = "statName") val statName: String,
    @ColumnInfo(name = "statBase") val statBase: Int,
    @PrimaryKey(autoGenerate = true) var statId: Int = 0
)