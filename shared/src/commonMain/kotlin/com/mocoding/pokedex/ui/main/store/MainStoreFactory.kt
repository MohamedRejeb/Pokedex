package com.mocoding.pokedex.ui.main.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.pokedexDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainStoreFactory(
    private val storeFactory: StoreFactory,
) {
    fun create(): MainStore =
        object : MainStore, Store<MainStore.Intent, MainStore.State, Nothing> by storeFactory.create(
            name = "MainStore",
            initialState = MainStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        data class PokemonSearchChanged(val search: String) : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Msg, Nothing>(
        pokedexDispatchers.main) {

        override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State): Unit =
            when (intent) {
                is MainStore.Intent.InputPokemonSearch -> dispatch(Msg.PokemonSearchChanged(intent.search))
            }
    }

    private object ReducerImpl: Reducer<MainStore.State, Msg> {
        override fun MainStore.State.reduce(msg: Msg): MainStore.State =
            when (msg) {
                is Msg.PokemonSearchChanged -> copy(search = msg.search)
            }
    }

}