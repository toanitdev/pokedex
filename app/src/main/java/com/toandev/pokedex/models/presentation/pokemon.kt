package com.toandev.pokedex.models.presentation

import com.toandev.pokedex.models.dto.response.PokemonDetailsRes

class Pokemon {
    var name: String = ""
    var type: List<Type>? = null
    var sprites: Sprites? = null

    fun load(pokemonDetailsRes: PokemonDetailsRes) : Pokemon {
        this.name = pokemonDetailsRes.name
        this.type = pokemonDetailsRes.types.map {
            Type().load(it)
        }
        this.sprites = Sprites().load(pokemonDetailsRes.sprites)

        return this
    }
}