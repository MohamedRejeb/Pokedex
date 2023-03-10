package com.mocoding.pokedex.ui.main.state

import androidx.compose.ui.graphics.Color
import com.mocoding.pokedex.ui.theme.*

data class CategoryState(
    val title: String,
    val iconUrl: String,
    val startColor: Color,
    val endColor: Color,
) {
    companion object {
        val pokedex = CategoryState(
            title = "Pokedex",
            iconUrl = "https://cdn-icons-png.flaticon.com/512/361/361998.png",
            startColor = Red300,
            endColor = Red500,
        )

        val moves = CategoryState(
            title = "Moves",
            iconUrl = "https://cdn-icons-png.flaticon.com/512/616/616494.png",
            startColor = Yellow300,
            endColor = Yellow500,
        )

        val evolutions = CategoryState(
            title = "Evolutions",
            iconUrl = "https://cdn-icons-png.flaticon.com/512/9077/9077327.png",
            startColor = Green300,
            endColor = Green500,
        )

        val locations = CategoryState(
            title = "Locations",
            iconUrl = "https://cdn-icons-png.flaticon.com/512/149/149060.png",
            startColor = Blue300,
            endColor = Blue500,
        )
    }
}
