package com.mocoding.pokedex.ui.details

import com.mocoding.pokedex.core.model.PokemonInfo

data class DetailsState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemonInfo: PokemonInfo? = null
)
