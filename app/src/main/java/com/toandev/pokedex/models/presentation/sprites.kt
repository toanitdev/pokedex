package com.toandev.pokedex.models.presentation

import com.toandev.pokedex.models.dto.response.SpriteRes

class Sprites {
    var frontDefault: String = ""

    fun load(sprites: SpriteRes) : Sprites {
        this.frontDefault = sprites.front_default
        return this
    }
}