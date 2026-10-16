package com.example.tarot_reader.data

import com.example.tarot_reader.data.remote.PokemonApiService
import com.example.tarot_reader.data.remote.RetrofitClient
import com.example.tarot_reader.model.TarotCard
import com.example.tarot_reader.util.Resultado
import kotlin.random.Random

class PokemonRepository(
    private val apiService: PokemonApiService = RetrofitClient.pokemonApiService
) {
    suspend fun obtenerCartasAleatorias(cantidad: Int = 5): Resultado<List<TarotCard>> {
        return try {
            val listaCartas = mutableListOf<TarotCard>()
            for (i in 1..cantidad) {
                val pokemonId = Random.nextInt(1, 151) // Selecciona entre los primeros 151 Pokémon
                val dto = apiService.obtenerPokemonPorId(pokemonId)
                listaCartas.add(dto.aModeloDominio())
            }

            if (listaCartas.isNotEmpty()) {
                Resultado.Exito(listaCartas)
            } else {
                Resultado.Error("No se encontraron Pokémon en PokéAPI")
            }
        } catch (e: Exception) {
            Resultado.Error(e.localizedMessage ?: "Error al conectar con PokéAPI")
        }
    }
}
