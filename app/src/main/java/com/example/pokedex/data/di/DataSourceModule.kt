package com.example.pokedex.data.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.data.database.PokemonLocalDataSource
import com.example.pokedex.data.database.dao.AbilityDao
import com.example.pokedex.data.database.dao.EggGroupDao
import com.example.pokedex.data.database.dao.PokemonDao
import com.example.pokedex.data.database.dao.StatsDao
import com.example.pokedex.data.database.dao.TypeDao
import com.example.pokedex.data.network.PokemonRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    //RETROFIT
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): PokemonRemoteDataSource =
        retrofit.create(PokemonRemoteDataSource::class.java)


    //ROOM
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): PokemonLocalDataSource {
        return Room.databaseBuilder(context, PokemonLocalDataSource::class.java, "pokemon_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun pokemonDao(db: PokemonLocalDataSource): PokemonDao = db.pokemonDao()

    @Singleton
    @Provides
    fun typeDao(db: PokemonLocalDataSource): TypeDao = db.typeDao()

    @Singleton
    @Provides
    fun abilityDao(db: PokemonLocalDataSource): AbilityDao = db.abilityDao()


    @Singleton
    @Provides
    fun eggGroupDao(db: PokemonLocalDataSource): EggGroupDao = db.eggGroupDao()

    @Singleton
    @Provides
    fun statsDao(db: PokemonLocalDataSource): StatsDao = db.statsDao()
}