package com.example.kanji.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = BgWhite,

    secondary = SecondaryButtonBg,
    onSecondary = SecondaryButtonText,

    background = AppBackground,
    onBackground = TextPrimary,

    surface = CardWhite,
    onSurface = TextPrimary,

    error = ErrorText,
    onError = BgWhite
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    onPrimary = BgWhite,

    secondary = SecondaryButtonBg,
    onSecondary = SecondaryButtonText,

    background = AppBackground,
    onBackground = TextPrimary,

    surface = CardWhite,
    onSurface = TextPrimary,

    error = ErrorText,
    onError = BgWhite
)

@Composable
fun KanjiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        window.statusBarColor = android.graphics.Color.parseColor("#F472B6")
        window.navigationBarColor = android.graphics.Color.parseColor("#F472B6")

        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.isAppearanceLightStatusBars = true
        insetsController.isAppearanceLightNavigationBars = true
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}