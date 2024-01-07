package com.toandev.pokedex.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toandev.pokedex.domain.repository.PokeRepository
import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.presentation.Pokemon
import com.toandev.pokedex.domain.usecase.GetFullPokemonList
import com.toandev.pokedex.models.entities.PokemonEntity
import com.toandev.pokedex.models.presentation.Sprites
import com.toandev.pokedex.models.presentation.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getFullPokemonList: GetFullPokemonList,
    val repository: PokeRepository
) : ViewModel() {

    var pokemons by mutableStateOf(emptyList<Pokemon>())


    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getPokemon() {

        compositeDisposable.add(getFullPokemonList.asObservable().subscribe({ list ->

            repository.insertAllPokemonLocal(list.map { pokemon ->
                PokemonEntity(
                    id = null,
                    number = null,
                    name = pokemon.name,
                    type = pokemon.type!!.get(0).name,
                    spriteUrl = pokemon.sprites!!.frontDefault
                )
            }).subscribe {

                repository.getAllPokemonLocal().map { pokeList ->
                    pokeList.map { pokemonEntity ->
                        Pokemon().also { p ->
                            p.name = pokemonEntity.name
                            p.type = listOf(Type().also { it.name = pokemonEntity.type })
                            p.sprites = Sprites().also { it.frontDefault = pokemonEntity.spriteUrl }
                        }
                    }
                }.subscribe() {
                    pokemons = it
                }

            }


//                pokemons = repository.getAllPokemonLocal().map { pokemonEntity ->
//                    Pokemon().also { p ->
//                        p.name = pokemonEntity.name
//                        p.type = listOf(Type().also { it.name = pokemonEntity.type })
//                        p.sprites = Sprites().also { it.frontDefault = pokemonEntity.spriteUrl }
//                    }
//                }

        }, {}))

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
