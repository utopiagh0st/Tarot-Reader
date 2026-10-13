package com.example.tarot_reader.ui.state

import com.example.tarot_reader.data.model.TarotCard

sealed class TarotUiState {
    object Loading : TarotUiState()
    data class Success(val cards: List<TarotCard>) : TarotUiState()
    data class Error(val message: String) : TarotUiState()
}
