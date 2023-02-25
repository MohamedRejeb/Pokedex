package com.mocoding.pokedex.core.network

object NetworkConstants {
    const val baseUrl = "https://pokeapi.co/api/v2/"

    object Pokemon {
        const val route = baseUrl + "pokemon"
        val byName: (String) -> String = { name -> "$route/$name"}
    }
}