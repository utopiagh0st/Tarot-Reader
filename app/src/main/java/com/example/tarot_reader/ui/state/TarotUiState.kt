package com.example.tarot_reader.ui.state

sealed class TarotUiState {
    object Loading : TarotUiState()
    data class Success : TarotUiState()
    data class Error : TarotUiState()
}