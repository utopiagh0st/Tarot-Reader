package com.example.tarot_reader.data

import com.example.tarot_reader.data.remote.RetrofitClient
import com.example.tarot_reader.data.remote.TarotApiService
import com.example.tarot_reader.model.TarotCard
import com.example.tarot_reader.util.Resultado

class TarotRepository(
    private val apiService: TarotApiService = RetrofitClient.apiService
) {
    suspend fun obtenerCartasAleatorias(cantidad: Int = 5): Resultado<List<TarotCard>> {
        return try {
            val dto = apiService.obtenerCartasAleatorias(cantidad)
            val dominio = dto.aListaModeloDominio()
            if (dominio.isNotEmpty()) {
                Resultado.Exito(dominio)
            } else {
                Resultado.Error("No se encontraron cartas en la API")
            }
        } catch (e: Exception) {
            Resultado.Error(e.localizedMessage ?: "Error de conexión con Tarot API")
        }
    }

    suspend fun obtenerTodasLasCartas(): Resultado<List<TarotCard>> {
        return try {
            val dto = apiService.obtenerTodasLasCartas()
            Resultado.Exito(dto.aListaModeloDominio())
        } catch (e: Exception) {
            Resultado.Error(e.localizedMessage ?: "Error de conexión con Tarot API")
        }
    }
}
