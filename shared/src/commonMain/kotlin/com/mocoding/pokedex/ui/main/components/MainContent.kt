package com.mocoding.pokedex.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mocoding.pokedex.ui.main.MainComponent
import com.mocoding.pokedex.ui.main.store.MainStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainContent(
    state: MainStore.State,
    onEvent: (MainStore.Intent) -> Unit,
    onOutput: (MainComponent.Output) -> Unit,
) {
    LaunchedEffect(state) {
        println(state.pokemonList.size)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pokedex KMP")
                }
            )
        }
    ) {  paddingValue ->
        Surface(
            modifier = Modifier
                .padding(paddingValue)
                .padding(20.dp)
        ) {
//            if (state.isLoading) {
//                Box(
//                    contentAlignment = Alignment.Center,
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    CircularProgressIndicator()
//                }
//            }

            state.error?.let { error ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = error)
                }
            }

            PokemonGrid(
                onPokemonClicked = { name ->
                    onOutput(MainComponent.Output.PokemonClicked(name = name))
                },
                pokemonList = state.pokemonList,
                loadMoreItems = {
                    if (state.pokemonList.isEmpty()) return@PokemonGrid

                    val nextPage = state.pokemonList.last().page + 1
                    onEvent(MainStore.Intent.LoadPokemonListByPage(page = nextPage))
                }
            )
        }
    }
}