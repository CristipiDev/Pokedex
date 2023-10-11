package com.example.pokedex.data.repository

import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.database.entity.TypeEntity
import com.example.pokedex.domain.model.TypeModel
import javax.inject.Inject

interface TypeRepository {

    suspend fun setNewType(newType: TypeModel): Int

    suspend fun setNewTypeIntoPokemonFromId(pokemonId: Int, typeId: Int)
}

class TypeRepositoryImpl @Inject constructor(
    private val typeDao: TypeDao
):TypeRepository {
    override suspend fun setNewType(newType: TypeModel): Int {
        return typeDao.insertNewType(TypeEntity(newType.typeName)).toInt()
    }

    override suspend fun setNewTypeIntoPokemonFromId(pokemonId: Int, typeId: Int) {
        TODO("Not yet implemented")
    }

}