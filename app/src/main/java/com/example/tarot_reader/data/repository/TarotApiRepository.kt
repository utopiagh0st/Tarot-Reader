package com.example.tarot_reader.data.repository

class TarotApiRepository {
    val apiService: TarotApiService
    
    fun getRandomCard()
    fun getAllCards()
    fun getCourtCards()
    fun getCardById()
}