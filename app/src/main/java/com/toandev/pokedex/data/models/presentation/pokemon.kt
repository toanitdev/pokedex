package com.toandev.pokedex.data.models.presentation

import com.toandev.pokedex.data.models.dto.response.PokemonDetailsRes

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