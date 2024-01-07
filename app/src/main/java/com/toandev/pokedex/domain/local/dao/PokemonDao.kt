package com.toandev.pokedex.domain.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toandev.pokedex.models.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    suspend fun getAll() : List<PokemonEntity>


    @Insert
    suspend fun insertAll(vararg pokes: PokemonEntity)

    @Insert
    suspend fun insert(vararg pokemon: PokemonEntity)
}