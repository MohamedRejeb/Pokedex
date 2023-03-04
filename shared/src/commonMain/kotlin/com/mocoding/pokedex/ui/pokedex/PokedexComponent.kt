package com.mocoding.pokedex.ui.pokedex

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.ui.pokedex.store.PokedexStore
import com.mocoding.pokedex.ui.pokedex.store.PokedexStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

class PokedexComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    pokemonRepository: PokemonRepository,
    searchValue: String,
    private val output: (Output) -> Unit
): ComponentContext by componentContext {

    private val pokedexStore =
        instanceKeeper.getStore {
            PokedexStoreFactory(
                storeFactory = storeFactory,
                searchValue = searchValue,
            ).create()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<PokedexStore.State> = pokedexStore.stateFlow

    fun onEvent(event: PokedexStore.Intent) {
        pokedexStore.accept(event)
    }

    fun onOutput(output: Output) {
        output(output)
    }

    sealed class Output {
        data class PokemonClicked(val name: String) : Output()
    }

}