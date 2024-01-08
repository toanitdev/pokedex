package com.toandev.pokedex.di.modules

import android.content.Context
import androidx.room.Room
import com.toandev.pokedex.data.local.PokeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class, ViewModelComponent::class)
object LocalDatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context:  Context) : PokeDatabase {
        return Room.databaseBuilder(context, PokeDatabase::class.java,"pokemon-database").build()
    }
}