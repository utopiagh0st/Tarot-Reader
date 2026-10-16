package com.example.tarot_reader.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL_TAROT = "https://tarotapi.dev/api/v1/"
    private const val BASE_URL_POKEMON = "https://pokeapi.co/api/v2/"

    val apiService: TarotApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TAROT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TarotApiService::class.java)
    }

    val pokemonApiService: PokemonApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_POKEMON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}
