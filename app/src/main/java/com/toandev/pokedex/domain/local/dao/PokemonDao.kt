package com.toandev.pokedex.domain.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toandev.pokedex.models.entities.PokemonEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAll() : Observable<List<PokemonEntity>>


    @Insert
    fun insertAll(vararg pokes: PokemonEntity) : Completable

    @Insert
    fun insert(vararg pokemon: PokemonEntity)
}