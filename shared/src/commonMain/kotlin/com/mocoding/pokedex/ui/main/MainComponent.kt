package com.mocoding.pokedex.ui.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.ui.main.store.MainStore
import com.mocoding.pokedex.ui.main.store.MainStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    pokemonRepository: PokemonRepository,
    private val output: (MainComponent.Output) -> Unit
): ComponentContext by componentContext {

    private val mainStore =
        instanceKeeper.getStore {
            MainStoreFactory(
                storeFactory = storeFactory,
                pokemonRepository = pokemonRepository
            ).create()
        }

    val state: Flow<MainState> = mainStore.states.map { it.toMainState() }

    sealed class Output {
        data class Selected(val name: String) : Output()
    }

}