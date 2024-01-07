package com.toandev.pokedex.domain.remote

import com.toandev.pokedex.domain.remote.service.PokeApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(var retrofit: Retrofit) {


    companion object {

        private var instance: RetrofitClient? = null
        fun getInstance(): RetrofitClient {
            if (instance == null) {
                instance = RetrofitClient(
                    Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                )
            }
            return instance!!
        }
    }


}

class RetrofitAdapter() {
    val pokeService: () -> PokeApiService = {
        RetrofitClient.getInstance().retrofit.create<PokeApiService>(PokeApiService::class.java)
    }
}