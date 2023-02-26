package com.mocoding.pokedex.ui.details.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mocoding.pokedex.core.model.PokemonInfo
import com.mocoding.pokedex.core.network.errors.PokedexException
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.pokedexDispatchers
import kotlinx.coroutines.launch

internal class DetailsStoreFactory(
    private val storeFactory: StoreFactory,
    private val pokemonRepository: PokemonRepository,
    private val pokemonName: String
) {
    fun create(): DetailsStore =
        object : DetailsStore, Store<DetailsStore.Intent, DetailsStore.State, Nothing> by storeFactory.create(
            name = "DetailsStore",
            initialState = DetailsStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        object PokemonInfoLoading : Msg()
        data class PokemonInfoLoaded(val pokemonInfo: PokemonInfo) : Msg()
        data class PokemonInfoFailed(val error: String?) : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<DetailsStore.Intent, Unit, DetailsStore.State, Msg, Nothing>(
        pokedexDispatchers.main) {
        override fun executeAction(action: Unit, getState: () -> DetailsStore.State) {
            loadPokemonInfoByName(pokemonName)
        }

        override fun executeIntent(intent: DetailsStore.Intent, getState: () -> DetailsStore.State): Unit =
            when (intent) {
                is DetailsStore.Intent.LoadPokemonInfoByName -> loadPokemonInfoByName(intent.name)
            }

        private fun loadPokemonInfoByName(name: String) {
            scope.launch {
                dispatch(Msg.PokemonInfoLoading)
                try {
                    val pokemonInfo = pokemonRepository.getPokemonByName(name)
                    dispatch(Msg.PokemonInfoLoaded(pokemonInfo))
                } catch (e: PokedexException) {
                    dispatch(Msg.PokemonInfoFailed(e.message))
                }
            }
        }
    }

    private object ReducerImpl: Reducer<DetailsStore.State, Msg> {
        override fun DetailsStore.State.reduce(msg: Msg): DetailsStore.State =
            when (msg) {
                is Msg.PokemonInfoLoading -> DetailsStore.State(isLoading = true)
                is Msg.PokemonInfoLoaded -> DetailsStore.State(pokemonInfo = msg.pokemonInfo)
                is Msg.PokemonInfoFailed -> DetailsStore.State(error = msg.error)
            }
    }

}