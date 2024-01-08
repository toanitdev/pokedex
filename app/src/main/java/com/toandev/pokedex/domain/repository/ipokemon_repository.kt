package com.toandev.pokedex.domain.repository

import com.toandev.pokedex.data.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.data.models.dto.response.PokemonListRes
import com.toandev.pokedex.data.models.entities.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


interface IPokeRepository {
    fun getPokemonDataList(): Observable<PokemonListRes>
    fun getPokemonDetails(name: String): Observable<PokemonDetailsRes>
    fun insertAllPokemonLocal(pokes: List<PokemonEntity>) : Completable
    fun getAllPokemonLocal(): Observable<List<PokemonEntity>>
}
