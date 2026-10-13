package com.example.tarot_reader.ui.fragment

import com.example.tarot_reader.data.model.TarotCard
import com.example.tarot_reader.ui.viewmodel.TarotViewModel

class TarotFragment(
    val viewModel: TarotViewModel
) {

    fun observeChanges() {
        // Observador de LiveData de uiState
    }

    fun showCard(card: TarotCard) {
        // Muestra una carta única
    }

    fun showList(cards: List<TarotCard>) {
        // Muestra una lista de cartas
    }
}
