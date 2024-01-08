package com.toandev.pokedex.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pokemon")
class PokemonEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val number: String?,
    val name: String,
    val type: String,
    val spriteUrl: String
)
