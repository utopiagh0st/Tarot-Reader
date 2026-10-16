package com.example.tarot_reader.data

import com.example.tarot_reader.data.remote.PokemonDetailDto
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

fun PokemonDetailDto.aModeloDominio(): TarotCard {
    val nombreFormateado = name?.replaceFirstChar { it.uppercase() } ?: "Pokémon Desconocido"
    val idPokemon = id ?: 0
    val tipoPrincipal = types?.firstOrNull()?.type?.name ?: "normal"

    return TarotCard(
        nombre = nombreFormateado,
        nombreCorto = "#${idPokemon.toString().padStart(3, '0')}",
        valor = idPokemon.toString(),
        valorInt = idPokemon,
        palo = tipoPrincipal.replaceFirstChar { it.uppercase() },
        tipo = "Pokémon",
        significadoDerecho = "Altura: ${(height ?: 0) / 10.0} m | Peso: ${(weight ?: 0) / 10.0} kg",
        significadoInvertido = "Experiencia Base: ${baseExperience ?: 0} PTS",
        descripcion = "Pokémon de tipo ${tipoPrincipal.uppercase()} traído desde PokéAPI.",
        simboloIcono = obtenerIconoPorTipoPokemon(tipoPrincipal)
    )
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

private fun obtenerIconoPorTipoPokemon(tipo: String): String {
    return when (tipo.lowercase()) {
        "fire" -> "🔥"
        "water" -> "💧"
        "grass" -> "🍃"
        "electric" -> "⚡"
        "psychic" -> "🔮"
        "ice" -> "❄️"
        "dragon" -> "🐉"
        "ghost" -> "👻"
        "dark" -> "🌙"
        "fairy" -> "✨"
        "fighting" -> "🥊"
        "poison" -> "☠️"
        "ground", "rock" -> "🪨"
        "bug" -> "🐛"
        "flying" -> "🦅"
        "steel" -> "⚙️"
        else -> "🔴"
    }
}
