package com.example.tarot_reader.data

import com.example.tarot_reader.data.remote.RetrofitClient
import com.example.tarot_reader.data.remote.PokemonApiService
import com.example.tarot_reader.model.TarotCard
import com.example.tarot_reader.util.Resultado

class PokemonRepository(
    private val apiService: PokemonApiService = RetrofitClient.pokemonApiService
) {
    suspend fun obtenerPokemonesComoCartas(cantidad: Int = 2): Resultado<List<TarotCard>> {
        return try {
            val listaPokemones = (1..cantidad).map {
                val randomId = (1..151).random() // ID aleatorio de Pokémon
                val dto = apiService.obtenerPokemonPorId(randomId)

                TarotCard(
                    nombre = dto.name.replaceFirstChar { it.uppercase() },
                    nombreCorto = "#${dto.id}",
                    valor = "Peso: ${dto.weight}",
                    valorInt = dto.id,
                    palo = "Pokédex",
                    tipo = "Pokémon",
                    significadoDerecho = "Altura: ${dto.height} decímetros",
                    significadoInvertido = "pokemoncito uwu",
                    descripcion = "un pokemon",
                    simboloIcono = "⚡"
                )
            }
            Resultado.Exito(listaPokemones)
        } catch (e: Exception) {
            Resultado.Error("Error al cargar Pokémon: ${e.localizedMessage}")
        }
    }
}