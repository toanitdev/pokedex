package com.toandev.pokedex.data.repository

import com.toandev.pokedex.data.local.PokeDatabase
import com.toandev.pokedex.data.remote.service.PokeApiService
import com.toandev.pokedex.data.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.data.models.dto.response.PokemonListRes
import com.toandev.pokedex.data.models.entities.PokemonEntity
import com.toandev.pokedex.domain.repository.IPokeRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


class PokeRepositoryImpl @Inject constructor(
    val service: PokeApiService,
    val pokeDatabase: PokeDatabase
) : IPokeRepository {
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