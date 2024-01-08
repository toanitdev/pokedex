package com.toandev.pokedex.data.models.presentation

import com.toandev.pokedex.data.models.dto.response.SpriteRes

class Sprites {
    var frontDefault: String = ""

    fun load(sprites: SpriteRes) : Sprites {
        this.frontDefault = sprites.front_default
        return this
    }
}