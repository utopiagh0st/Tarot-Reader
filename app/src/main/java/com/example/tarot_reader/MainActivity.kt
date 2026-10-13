package com.example.tarot_reader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tarot_reader.data.network.TarotApiService
import com.example.tarot_reader.data.repository.TarotApiRepository
import com.example.tarot_reader.ui.TarotHomeScreen
import com.example.tarot_reader.ui.theme.Tarot_ReaderTheme
import com.example.tarot_reader.ui.viewmodel.TarotViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiService = object : TarotApiService {
            override val baseUrl: String = "https://tarotapi.dev/api/v1/"
            override val client: OkHttpClient = OkHttpClient.Builder().build()

            private val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            private val delegate = retrofit.create(TarotApiService::class.java)

            override fun getRandomCard() = delegate.getRandomCard()
            override fun getAllCards() = delegate.getAllCards()
            override fun getCourtCards() = delegate.getCourtCards()
            override fun getCardById(id: String) = delegate.getCardById(id)
        }

        val repository = TarotApiRepository(apiService)
        val viewModel = TarotViewModel(repository)

        setContent {
            Tarot_ReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TarotHomeScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
