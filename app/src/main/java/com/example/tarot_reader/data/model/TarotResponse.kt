package com.example.tarot_reader.data.model

class TarotResponse {
    val nhits: Int
    val cards: List<TarotCard>
    
    fun getCards()
}