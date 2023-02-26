package com.mocoding.pokedex.ui.main

import com.mocoding.pokedex.core.model.Pokemon

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemonList: List<Pokemon> = emptyList()
)
