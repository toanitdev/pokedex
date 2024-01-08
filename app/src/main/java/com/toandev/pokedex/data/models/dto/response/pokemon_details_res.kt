package com.toandev.pokedex.data.models.dto.response

data class PokemonDetailsRes(
    val name: String,
    val sprites: SpriteRes,
    val types: List<TypesRes>
)