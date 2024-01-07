package com.toandev.pokedex.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toandev.pokedex.domain.local.dao.PokemonDao
import com.toandev.pokedex.models.entities.PokemonEntity

@Database(entities = [PokemonEntity::class,],version = 1,)
abstract class PokeDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao
}