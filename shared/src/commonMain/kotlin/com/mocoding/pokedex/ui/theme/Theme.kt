package com.mocoding.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Blue400,
    secondary = Blue500,

    background = DarkBlue700,
    surface = DarkBlue500,
    surfaceVariant = DarkBlue400,
    onPrimary = Color.White,
    onSecondary = LightGray200,
    onBackground = LightGray200,
    onSurface = LightGray200,
    onSurfaceVariant = LightGray300,
    outline = Gray400
)

private val LightColorScheme = lightColorScheme(
    primary = Blue400,
    secondary = Blue500,

    background = DarkBlue700,
    surface = DarkBlue500,
    surfaceVariant = DarkBlue400,
    onPrimary = Color.White,
    onSecondary = LightGray200,
    onBackground = LightGray200,
    onSurface = LightGray200,
    onSurfaceVariant = LightGray300,
    outline = Gray400
)

@Composable
internal fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}