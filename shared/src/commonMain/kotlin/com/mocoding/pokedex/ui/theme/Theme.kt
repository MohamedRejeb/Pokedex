package com.mocoding.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Red400,
    secondary = Green400,
    tertiary = Blue400,

    background = Black,
    surface = DarkGray400,
    surfaceVariant = Gray400,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = LightGray400,
    outline = LightGray400
)

private val LightColorScheme = lightColorScheme(
    primary = Red400,
    secondary = Green400,
    tertiary = Blue400,

    background = Color.White,
    surface = LightGray400,
    surfaceVariant = Gray400,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Black,
    onSurface = Black,
    onSurfaceVariant = DarkGray400,
    outline = LightGray400
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