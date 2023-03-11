package com.mocoding.pokedex.ui.favorite.store

import com.arkivanov.mvikotlin.core.store.Store
import com.mocoding.pokedex.core.model.Pokemon

interface FavoriteStore: Store<FavoriteStore.Intent, FavoriteStore.State, Nothing> {

    sealed class Intent

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val pokemonList: List<Pokemon> = emptyList(),
    )

}