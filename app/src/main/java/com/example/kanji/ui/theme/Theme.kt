package com.example.kanji.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val KanjiColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = CardWhite,

    secondary = GreenLight,
    onSecondary = TextPrimary,

    background = BackgroundSoft,
    onBackground = TextPrimary,

    surface = CardWhite,
    onSurface = TextPrimary,

    error = WrongRed,
    outline = BorderSoft
)

@Composable
fun KanjiTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = KanjiColorScheme,
        typography = Typography,
        content = content
    )
}