package com.mocoding.pokedex.ui.favorite.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mocoding.pokedex.core.model.Pokemon
import com.mocoding.pokedex.data.repository.PokemonRepository
import com.mocoding.pokedex.pokedexDispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FavoriteStoreFactory(
    private val storeFactory: StoreFactory,
): KoinComponent {

    private val pokemonRepository by inject<PokemonRepository>()

    fun create(): FavoriteStore =
        object : FavoriteStore, Store<FavoriteStore.Intent, FavoriteStore.State, Nothing> by storeFactory.create(
            name = "FavoriteStore",
            initialState = FavoriteStore.State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Msg {
        object PokemonListLoading : Msg()
        data class PokemonListLoaded(val pokemonList: List<Pokemon>) : Msg()
        data class PokemonListFailed(val error: String?) : Msg()
    }

    private inner class ExecutorImpl : CoroutineExecutor<FavoriteStore.Intent, Unit, FavoriteStore.State, Msg, Nothing>(
        pokedexDispatchers.main) {
        override fun executeAction(action: Unit, getState: () -> FavoriteStore.State) {
            getFavoritePokemonList()
        }

        override fun executeIntent(intent: FavoriteStore.Intent, getState: () -> FavoriteStore.State): Unit = Unit

        private var getFavoritePokemonListJob: Job? = null
        private fun getFavoritePokemonList() {
            if (getFavoritePokemonListJob?.isActive == true) return

            getFavoritePokemonListJob = scope.launch {
                dispatch(Msg.PokemonListLoading)

                pokemonRepository.getFavoritePokemonListFlow().collectLatest { favoritePokemonList ->
                    dispatch(Msg.PokemonListLoaded(favoritePokemonList))
                }
            }
        }
    }

    private object ReducerImpl: Reducer<FavoriteStore.State, Msg> {
        override fun FavoriteStore.State.reduce(msg: Msg): FavoriteStore.State =
            when (msg) {
                is Msg.PokemonListLoading -> copy(isLoading = true)
                is Msg.PokemonListLoaded -> FavoriteStore.State(pokemonList = msg.pokemonList)
                is Msg.PokemonListFailed -> copy(error = msg.error)
            }
    }
}