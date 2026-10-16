package com.example.tarot_reader.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon/{id}")
    suspend fun obtenerPokemonPorId(@Path("id") id: Int): PokemonDto
}