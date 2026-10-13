package com.example.tarot_reader.data.repository

import com.example.tarot_reader.data.model.TarotCard
import com.example.tarot_reader.data.network.TarotApiService

class TarotApiRepository(
    val apiService: TarotApiService
) {

    fun getRandomCard(): TarotCard {
        val response = apiService.getRandomCard().execute()
        return response.body()?.getCards()?.first() ?: throw Exception("Carta no encontrada")
    }

    fun getAllCards(): List<TarotCard> {
        val response = apiService.getAllCards().execute()
        return response.body()?.getCards() ?: emptyList()
    }

    fun getCourtCards(): List<TarotCard> {
        val response = apiService.getCourtCards().execute()
        return response.body()?.getCards() ?: emptyList()
    }

    fun getCardById(id: String): TarotCard {
        val response = apiService.getCardById(id).execute()
        return response.body()?.getCards()?.first() ?: throw Exception("Carta no encontrada")
    }
}
