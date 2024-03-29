package com.toandev.pokedex.domain.usecase

import com.toandev.pokedex.domain.repository.PokeRepository
import com.toandev.pokedex.models.dto.response.PokemonDetailsRes
import com.toandev.pokedex.models.presentation.Pokemon
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetFullPokemonList @Inject constructor(val repository: PokeRepository)  :  UseCase<Void, List<Pokemon>>(){
    override fun createObservable(p: Void?): Observable<List<Pokemon>> {
        return  repository.getPokemonDataList().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).flatMap {
                Observable.fromIterable(it.results)
            }.flatMap {
                repository.getPokemonDetails(it.name).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
            }.toList().map {
                val list = mutableListOf<Pokemon>()
                it.forEach { details ->
                    list.add(Pokemon().load(details))
                }
                list.toList()
            }.toObservable()
    }
}