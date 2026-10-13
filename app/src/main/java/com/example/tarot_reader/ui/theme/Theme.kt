package com.example.tarot_reader.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TarotColorScheme = darkColorScheme(
    primary = CelestialGold,
    onPrimary = MidnightBackground,
    primaryContainer = MysticPurple,
    onPrimaryContainer = CelestialGoldGlow,
    secondary = MysticRoseGold,
    onSecondary = MidnightBackground,
    background = MidnightBackground,
    onBackground = MysticTextPrimary,
    surface = MysticSurface,
    onSurface = MysticTextPrimary,
    surfaceVariant = MysticCardSurface,
    onSurfaceVariant = MysticTextSecondary,
    outline = CelestialGold
)

@Composable
fun Tarot_ReaderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TarotColorScheme,
        typography = Typography,
        content = content
    )
}
