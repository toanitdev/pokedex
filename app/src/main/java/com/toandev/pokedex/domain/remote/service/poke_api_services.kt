package com.toandev.pokedex.domain.remote.service

import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.dto.response.PokemonListRes
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon?offset=20&limit=20")
    fun getPokemonList() : Observable<PokemonListRes>

    @GET("pokemon/{name}")
    fun getPokemonDetails(@Path("name") name:String) : Observable<PokemonDetailsRes>
}