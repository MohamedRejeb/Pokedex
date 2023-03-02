package com.mocoding.pokedex.ui.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.ui.details.store.DetailsStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    pokemonRepository: PokemonRepository,
    pokemonName: String,
    private val output: (Output) -> Unit
): ComponentContext by componentContext {

    private val detailsStore =
        instanceKeeper.getStore {
            DetailsStoreFactory(
                storeFactory = storeFactory,
                pokemonRepository = pokemonRepository,
                pokemonName = pokemonName
            ).create()
        }

    val state: Flow<DetailsState> = detailsStore.states.map { it.toDetailsState() }

    fun onOutput(output: Output) {
        output(output)
    }

    sealed class Output {
        object Back : Output()
    }

}