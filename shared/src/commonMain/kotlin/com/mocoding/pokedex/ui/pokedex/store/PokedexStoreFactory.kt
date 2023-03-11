package com.mocoding.pokedex.ui.pokedex.store

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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class PokedexStoreFactory(
    private val storeFactory: StoreFactory,
    private val searchValue: String,
): KoinComponent {

    private val pokemonRepository by inject<PokemonRepository>()

    fun create(): PokedexStore =
        object : PokedexStore, Store<PokedexStore.Intent, PokedexStore.State, Nothing> by storeFactory.create(
            name = "PokedexStore",
            initialState = PokedexStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        object PokemonListLoading : Msg()
        data class PokemonListLoaded(val pokemonList: List<Pokemon>) : Msg()
        data class PokemonListFailed(val error: String?) : Msg()
        data class SearchValueUpdated(val searchValue: String) : Msg()
        object LastPageLoaded : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<PokedexStore.Intent, Unit, PokedexStore.State, Msg, Nothing>(
        pokedexDispatchers.main) {
        override fun executeAction(action: Unit, getState: () -> PokedexStore.State) {
            loadPokemonListByPage(page = 0)
        }

        override fun executeIntent(intent: PokedexStore.Intent, getState: () -> PokedexStore.State): Unit =
            when (intent) {
                is PokedexStore.Intent.LoadPokemonListByPage -> loadPokemonListByPage(intent.page, getState().isLastPageLoaded)
                is PokedexStore.Intent.UpdateSearchValue -> dispatch(Msg.SearchValueUpdated(intent.searchValue))
            }

        private var loadPokemonListByPageJob: Job? = null
        private fun loadPokemonListByPage(
            page: Long,
            isLastPageLoaded: Boolean = false
        ) {
            if (loadPokemonListByPageJob?.isActive == true) return
            if (isLastPageLoaded) return

            loadPokemonListByPageJob = scope.launch {
                dispatch(Msg.PokemonListLoading)

                pokemonRepository
                    .getPokemonList(page)
                    .onSuccess { pokemonList ->
                        if (pokemonList.isEmpty()) {
                            dispatch(Msg.LastPageLoaded)
                        } else {
                            dispatch(Msg.PokemonListLoaded(pokemonList))
                        }
                    }
                    .onFailure { e ->
                        dispatch(Msg.PokemonListFailed(e.message))
                    }
            }
        }
    }

    private object ReducerImpl: Reducer<PokedexStore.State, Msg> {
        override fun PokedexStore.State.reduce(msg: Msg): PokedexStore.State =
            when (msg) {
                is Msg.PokemonListLoading -> copy(isLoading = true)
                is Msg.PokemonListLoaded -> PokedexStore.State(pokemonList = pokemonList + msg.pokemonList)
                is Msg.PokemonListFailed -> copy(error = msg.error)
                is Msg.SearchValueUpdated -> copy(searchValue = msg.searchValue)
                Msg.LastPageLoaded -> copy(isLastPageLoaded = true)
            }
    }
}