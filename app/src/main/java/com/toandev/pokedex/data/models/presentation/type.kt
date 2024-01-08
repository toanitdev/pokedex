package com.toandev.pokedex.data.models.presentation

import com.toandev.pokedex.data.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.data.models.dto.response.TypeRes
import com.toandev.pokedex.data.models.dto.response.TypesRes

class Type {
    var slot: Int = 0
    var name: String = ""
    var url: String = ""


    fun load(types: TypesRes) : Type {
        this.name = types.type.name
        this.url = types.type.url
        this.slot = types.slot
        return this
    }
}