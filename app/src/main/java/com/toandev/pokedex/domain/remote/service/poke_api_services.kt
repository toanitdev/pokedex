package com.toandev.pokedex.domain.remote.service

import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.dto.response.PokemonListRes
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?offset=20&limit=20")
    suspend fun getPokemonList() : PokemonListRes

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name:String) : PokemonDetailsRes
}