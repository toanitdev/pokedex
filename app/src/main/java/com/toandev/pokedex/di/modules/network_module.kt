package com.toandev.pokedex.di.modules

import com.toandev.pokedex.domain.remote.service.PokeApiService
import com.toandev.pokedex.domain.repository.PokeRepository
import com.toandev.pokedex.domain.repository.PokeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class,ViewModelComponent::class)
object NetworkModule {
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePokeService(retrofit: Retrofit) : PokeApiService {
        return retrofit.create<PokeApiService>(PokeApiService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class,ViewModelComponent::class)
abstract class Repository {
    @Binds
    abstract fun bindPokeRepository(pokeRepositoryImpl: PokeRepositoryImpl) : PokeRepository
}