package com.example.tarot_reader.data.network

import com.example.tarot_reader.data.model.TarotResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TarotApiService {
    val baseUrl: String
    val client: OkHttpClient

    @GET("cards/random")
    fun getRandomCard(): Call<TarotResponse>

    @GET("cards/all")
    fun getAllCards(): Call<TarotResponse>

    @GET("cards/courts")
    fun getCourtCards(): Call<TarotResponse>

    @GET("cards/{id}")
    fun getCardById(@Path("id") id: String): Call<TarotResponse>
}
