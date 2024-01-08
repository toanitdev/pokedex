package com.toandev.pokedex.data.models.dto.response

data class PokemonListRes(
    val count: Int,
    val next: String,
    val previous: String,
    val img: String,
    val results: List<PokemonRes>
)