package com.toandev.pokedex.domain.repository

import com.toandev.pokedex.domain.local.PokeDatabase
import com.toandev.pokedex.domain.remote.service.PokeApiService
import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.dto.response.PokemonListRes
import com.toandev.pokedex.models.entities.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


interface PokeRepository {
    fun getPokemonDataList(): Observable<PokemonListRes>
    fun getPokemonDetails(name: String): Observable<PokemonDetailsRes>
    fun insertAllPokemonLocal(pokes: List<PokemonEntity>) : Completable
    fun getAllPokemonLocal(): Observable<List<PokemonEntity>>
}

class PokeRepositoryImpl @Inject constructor(
    val service: PokeApiService,
    val pokeDatabase: PokeDatabase
) : PokeRepository {
    override fun getPokemonDataList(): Observable<PokemonListRes> = service.getPokemonList()
    override fun getPokemonDetails(name: String): Observable<PokemonDetailsRes> =
        service.getPokemonDetails(name)

    override fun insertAllPokemonLocal(pokes: List<PokemonEntity>) : Completable {
        return pokeDatabase.pokemonDao().insertAll(*pokes.toTypedArray())
    }

    override fun getAllPokemonLocal(): Observable<List<PokemonEntity>> {
        return pokeDatabase.pokemonDao().getAll()
    }
}