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
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/pokeball.png",
            startColor = Red300,
            endColor = Red500,
        )

        val moves = CategoryState(
            title = "Moves",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/electric.png",
            startColor = Yellow300,
            endColor = Yellow500,
        )

        val evolutions = CategoryState(
            title = "Evolutions",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/dna.png",
            startColor = Green300,
            endColor = Green500,
        )

        val locations = CategoryState(
            title = "Locations",
            iconUrl = "https://raw.githubusercontent.com/M0Coding/Pokedex/main/icons/location.png",
            startColor = Blue300,
            endColor = Blue500,
        )
    }
}
