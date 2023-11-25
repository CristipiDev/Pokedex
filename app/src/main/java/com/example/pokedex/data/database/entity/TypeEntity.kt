package com.example.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_type")
data class TypeEntity (
    @ColumnInfo(name = "type") val typeName: String,
    @PrimaryKey(autoGenerate = true) var typeId: Int = 0
)