package com.mocoding.pokedex.ui.main.store

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.Store
import com.mocoding.pokedex.core.model.Pokemon

internal interface MainStore: Store<MainStore.Intent, MainStore.State, Nothing> {

    sealed class Intent {
        data class LoadPokemonListByPage(val page: Long): Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val pokemonList: List<Pokemon> = emptyList()
    )

}