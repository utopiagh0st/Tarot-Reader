package com.example.tarot_reader.data

import com.example.tarot_reader.data.remote.TarotCardDto
import com.example.tarot_reader.data.remote.TarotResponseDto
import com.example.tarot_reader.model.TarotCard

fun TarotCardDto.aModeloDominio(): TarotCard {
    return TarotCard(
        nombre = name ?: "Carta Desconocida",
        nombreCorto = nameShort ?: "",
        valor = value ?: "0",
        valorInt = valueInt ?: 0,
        palo = suit,
        tipo = type ?: "Arcano",
        significadoDerecho = meaningUp ?: "Sin significado disponible.",
        significadoInvertido = meaningRev ?: "Sin significado disponible.",
        descripcion = desc ?: "",
        simboloIcono = obtenerSimboloPorTipo(type, suit)
    )
}

fun TarotResponseDto.aListaModeloDominio(): List<TarotCard> {
    return cards?.map { it.aModeloDominio() } ?: emptyList()
}

private fun obtenerSimboloPorTipo(tipo: String?, palo: String?): String {
    return when {
        tipo?.lowercase() == "major" -> "🃏"
        palo?.lowercase() == "wands" -> "🪄"
        palo?.lowercase() == "cups" -> "🍷"
        palo?.lowercase() == "swords" -> "⚔️"
        palo?.lowercase() == "pentacles" -> "🪙"
        else -> "✨"
    }
}
