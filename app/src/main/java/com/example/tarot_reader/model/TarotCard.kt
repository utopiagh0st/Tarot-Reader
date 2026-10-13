package com.example.tarot_reader.model

data class TarotCard(
    val nombre: String,
    val nombreCorto: String,
    val valor: String,
    val valorInt: Int,
    val palo: String?,
    val tipo: String,
    val significadoDerecho: String,
    val significadoInvertido: String,
    val descripcion: String,
    val simboloIcono: String = "✨"
)
