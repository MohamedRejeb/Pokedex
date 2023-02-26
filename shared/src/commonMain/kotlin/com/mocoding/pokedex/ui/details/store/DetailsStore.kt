package com.mocoding.pokedex.ui.details.store

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.Store
import com.mocoding.pokedex.core.model.PokemonInfo

interface DetailsStore: Store<DetailsStore.Intent, DetailsStore.State, Nothing> {

    sealed class Intent {
        data class LoadPokemonInfoByName(val name: String): Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val pokemonInfo: PokemonInfo? = null
    )

}