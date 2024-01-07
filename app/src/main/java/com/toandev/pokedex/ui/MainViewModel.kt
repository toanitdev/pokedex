package com.toandev.pokedex.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toandev.pokedex.domain.usecase.GetAllPokemonLocal
import com.toandev.pokedex.models.presentation.Pokemon
import com.toandev.pokedex.domain.usecase.GetFullPokemonList
import com.toandev.pokedex.domain.usecase.SavePokemons
import com.toandev.pokedex.models.entities.PokemonEntity
import com.toandev.pokedex.models.presentation.Sprites
import com.toandev.pokedex.models.presentation.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getFullPokemonList: GetFullPokemonList,
    val savePokemons: SavePokemons,
    val getAllPokemonLocal: GetAllPokemonLocal
) : ViewModel() {

    var pokemons by mutableStateOf(emptyList<Pokemon>())

    @SuppressLint("CheckResult")
    fun getPokemon() {
        viewModelScope.launch {
            val res = getFullPokemonList.call()
            savePokemons.call(*res.map {
                PokemonEntity(null, null,it.name,it.types.get(0).type.name,it.sprites.front_default)
            }.toTypedArray())

            pokemons = getAllPokemonLocal.call().map { pokeEntity ->
                Pokemon().also {
                    it.name = pokeEntity.name
                    it.type = listOf(Type().also { it.name = pokeEntity.type })
                    it.sprites = Sprites().also { it.frontDefault = pokeEntity.spriteUrl }
                }
            }
        }
    }


}
