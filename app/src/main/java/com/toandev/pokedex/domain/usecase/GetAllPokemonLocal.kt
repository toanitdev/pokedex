package com.toandev.pokedex.domain.usecase

import com.toandev.pokedex.domain.repository.PokeRepository
import com.toandev.pokedex.models.entities.PokemonEntity
import javax.inject.Inject

class GetAllPokemonLocal @Inject constructor(val repository: PokeRepository) {

    suspend fun call() : List<PokemonEntity>{
        return repository.getAllPokemonLocal()
    }
}