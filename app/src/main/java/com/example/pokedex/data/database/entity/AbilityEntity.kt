package com.example.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_ability")
data class AbilityEntity (
    @ColumnInfo(name = "ability") val abilityName: String,
    @PrimaryKey(autoGenerate = true) var abilityId: Int = 0
)