package com.mocoding.pokedex.ui.details

import com.mocoding.pokedex.ui.details.store.DetailsStore

internal fun DetailsStore.State.toDetailsState() = DetailsState(
    isLoading = isLoading,
    error = error,
    pokemonInfo = pokemonInfo
)