package com.example.tarot_reader.ui.state

import com.example.tarot_reader.model.TarotCard

sealed interface TarotUiState {
    object Cargando : TarotUiState
    data class Exito(val cartas: List<TarotCard>) : TarotUiState
    data class Error(val mensaje: String) : TarotUiState
}
