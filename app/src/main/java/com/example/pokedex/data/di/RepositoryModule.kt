package com.example.pokedex.data.di

import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun pokemonRepository(repo: PokemonRepositoryImpl): PokemonRepository
}