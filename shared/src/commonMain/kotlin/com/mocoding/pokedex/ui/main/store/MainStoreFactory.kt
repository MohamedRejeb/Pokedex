package com.mocoding.pokedex.ui.main.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.pokedexDispatchers
import kotlinx.coroutines.launch

internal class MainStoreFactory(
    private val storeFactory: StoreFactory,
    private val pokemonRepository: PokemonRepository,
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
        object PokemonListLoading : Msg()
        data class PokemonListLoaded(val pokemonList: List<Pokemon>) : Msg()
        data class PokemonListFailed(val error: String?) : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Msg, Nothing>(
        pokedexDispatchers.main) {
        override fun executeAction(action: Unit, getState: () -> MainStore.State) {
            loadPokemonListByPage(page = 1)
        }

        override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State): Unit =
            when (intent) {
                is MainStore.Intent.LoadPokemonListByPage -> loadPokemonListByPage(intent.page)
            }

        private fun loadPokemonListByPage(page: Long) {
            scope.launch {
                dispatch(Msg.PokemonListLoading)

                pokemonRepository
                    .getPokemonList(page)
                    .onSuccess { pokemonList ->
                        dispatch(Msg.PokemonListLoaded(pokemonList))
                    }
                    .onFailure { e ->
                        dispatch(Msg.PokemonListFailed(e.message))
                    }
            }
        }
    }

    private object ReducerImpl: Reducer<MainStore.State, Msg> {
        override fun MainStore.State.reduce(msg: Msg): MainStore.State =
            when (msg) {
                is Msg.PokemonListLoading -> MainStore.State(isLoading = true)
                is Msg.PokemonListLoaded -> MainStore.State(pokemonList = msg.pokemonList)
                is Msg.PokemonListFailed -> MainStore.State(error = msg.error)
            }
    }

}