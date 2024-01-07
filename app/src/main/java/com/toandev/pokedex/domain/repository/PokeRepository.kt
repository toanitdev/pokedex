package com.toandev.pokedex.domain.repository

import com.toandev.pokedex.domain.local.PokeDatabase
import com.toandev.pokedex.domain.remote.service.PokeApiService
import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.dto.response.PokemonListRes
import com.toandev.pokedex.models.entities.PokemonEntity
import javax.inject.Inject


interface PokeRepository {
    suspend fun getPokemonDataList(): PokemonListRes
    suspend fun getPokemonDetails(name: String): PokemonDetailsRes
    suspend fun insertAllPokemonLocal(pokes: List<PokemonEntity>)
    suspend fun getAllPokemonLocal(): List<PokemonEntity>
}

class PokeRepositoryImpl @Inject constructor(
    val service: PokeApiService,
    val pokeDatabase: PokeDatabase
) : PokeRepository {
    override suspend fun getPokemonDataList(): PokemonListRes = service.getPokemonList()
    override suspend fun getPokemonDetails(name: String): PokemonDetailsRes =
        service.getPokemonDetails(name)

    override suspend fun insertAllPokemonLocal(pokes: List<PokemonEntity>) {
        return pokeDatabase.pokemonDao().insertAll(*pokes.toTypedArray())
    }

    override suspend fun getAllPokemonLocal() : List<PokemonEntity> {
        return pokeDatabase.pokemonDao().getAll()
    }
}