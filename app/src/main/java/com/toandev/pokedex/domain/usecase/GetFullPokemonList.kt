package com.toandev.pokedex.domain.usecase

import com.toandev.pokedex.domain.repository.PokeRepository
import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import javax.inject.Inject

class GetFullPokemonList @Inject constructor(val repository: PokeRepository)  {
    suspend fun call() : List<PokemonDetailsRes> {
        return repository.getPokemonDataList().results.map{res ->
            repository.getPokemonDetails(res.name)
        }
    }
}