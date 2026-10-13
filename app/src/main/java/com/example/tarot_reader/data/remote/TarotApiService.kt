package com.example.tarot_reader.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TarotApiService {
    @GET("cards/random")
    suspend fun obtenerCartasAleatorias(@Query("n") cantidad: Int = 5): TarotResponseDto

    @GET("cards/all")
    suspend fun obtenerTodasLasCartas(): TarotResponseDto

    @GET("cards/courts")
    suspend fun obtenerCartasDeCorte(): TarotResponseDto

    @GET("cards/{id}")
    suspend fun obtenerCartaPorId(@Path("id") id: String): TarotResponseDto
}
