package com.example.tarot_reader.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tarot_reader.data.TarotRepository
import com.example.tarot_reader.data.PokemonRepository
import com.example.tarot_reader.ui.state.TarotUiState
import com.example.tarot_reader.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TarotViewModel(
    private val repository: TarotRepository = TarotRepository(),
    private val pokemonRepository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<TarotUiState>(TarotUiState.Cargando)
    val uiState: StateFlow<TarotUiState> = _uiState.asStateFlow()

    init {
        cargarCincoCartasAleatorias()
    }

    fun cargarCincoCartasAleatoriassinusar() {
        _uiState.value = TarotUiState.Cargando
        viewModelScope.launch {
            when (val resultado = repository.obtenerCartasAleatorias(5)) {
                is Resultado.Exito -> _uiState.value = TarotUiState.Exito(resultado.datos)
                is Resultado.Error -> _uiState.value = TarotUiState.Error(resultado.mensaje)
            }
        }
    }

    fun cargarCincoCartasAleatorias() {
        _uiState.value = TarotUiState.Cargando
        viewModelScope.launch {
            val resTarot = repository.obtenerCartasAleatorias(3)
            val resPokemon = pokemonRepository.obtenerPokemonesComoCartas(2)

            if (resTarot is Resultado.Exito && resPokemon is Resultado.Exito) {

                val listaCombinada = resTarot.datos + resPokemon.datos
                _uiState.value = TarotUiState.Exito(listaCombinada)
            } else {
                _uiState.value = TarotUiState.Error("Error al consultar")
            }
        }
    }
}
