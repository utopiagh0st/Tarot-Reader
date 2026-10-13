package com.example.tarot_reader.data.model

data class TarotResponse(
    val nhits: Int,
    val cards: List<TarotCard>
) {
    @JvmName("getCardsListCustom")
    fun getCards(): List<TarotCard> {
        return cards
    }
}
