package com.example.tarot_reader.ui.viewmodel

class TarotViewModel {
    val repository: TarotApiRepository
    val _uiState: MutableLiveData<TarotUiState>
    val uiState: LiveData<TarotUiState>
    
    fun getRandomCard()
    fun getAllCards()
    fun getCourtCards()
    fun getCardById()
}