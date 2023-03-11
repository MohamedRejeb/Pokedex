package com.mocoding.pokedex.ui.utils

import androidx.compose.ui.graphics.Color
import com.mocoding.pokedex.ui.theme.*

object PokemonAbilityUtils {

     fun getAbilityColor(name: String): Color = when(name) {
         "fighting" -> Fighting
         "flying" -> Flying
         "poison" -> Poison
         "ground" -> Ground
         "rock" -> Rock
         "bug" -> Bug
         "ghost" -> Ghost
         "steel" -> Steel
         "fire" -> Fire
         "water" -> Water
         "grass" -> Grass
         "electric" -> Electric
         "psychic" -> Psychic
         "ice" -> Ice
         "dragon" -> Dragon
         "fairy" -> Fairy
         "dark" -> Dark
         else -> Gray400
     }

}