package com.mocoding.pokedex.ui.main

import com.mocoding.pokedex.ui.main.store.MainStore

internal fun MainStore.State.toMainState() = MainState(
    isLoading = isLoading,
    error = error,
    pokemonList = pokemonList
)