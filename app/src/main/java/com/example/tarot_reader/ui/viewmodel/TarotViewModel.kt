package com.example.tarot_reader.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarot_reader.data.repository.TarotApiRepository
import com.example.tarot_reader.ui.state.TarotUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TarotViewModel(
    val repository: TarotApiRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<TarotUiState>()
    val uiState: LiveData<TarotUiState> = _uiState

    fun getRandomCard() {
        _uiState.value = TarotUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val card = repository.getRandomCard()
                _uiState.postValue(TarotUiState.Success(listOf(card)))
            } catch (e: Exception) {
                _uiState.postValue(TarotUiState.Error(e.localizedMessage ?: "Error al conectar con la API"))
            }
        }
    }

    fun getAllCards() {
        _uiState.value = TarotUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cards = repository.getAllCards()
                _uiState.postValue(TarotUiState.Success(cards))
            } catch (e: Exception) {
                _uiState.postValue(TarotUiState.Error(e.localizedMessage ?: "Error al conectar con la API"))
            }
        }
    }

    fun getCourtCards() {
        _uiState.value = TarotUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cards = repository.getCourtCards()
                _uiState.postValue(TarotUiState.Success(cards))
            } catch (e: Exception) {
                _uiState.postValue(TarotUiState.Error(e.localizedMessage ?: "Error al conectar con la API"))
            }
        }
    }

    fun getCardById(id: String) {
        _uiState.value = TarotUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val card = repository.getCardById(id)
                _uiState.postValue(TarotUiState.Success(listOf(card)))
            } catch (e: Exception) {
                _uiState.postValue(TarotUiState.Error(e.localizedMessage ?: "Error al conectar con la API"))
            }
        }
    }
}
