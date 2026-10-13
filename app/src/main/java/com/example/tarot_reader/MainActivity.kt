package com.example.tarot_reader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tarot_reader.ui.TarotHomeScreen
import com.example.tarot_reader.ui.theme.Tarot_ReaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarot_ReaderTheme {
                TarotHomeScreen()
            }
        }
    }
}
