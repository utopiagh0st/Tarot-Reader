package com.example.tarot_reader.data.network

class TarotApiService {
    val baseUrl: String
    val client: OkHttpClient
    
    fun getRandomCard()
    fun getAllCards()
    fun getCourtCards()
    fun getCardById()
}