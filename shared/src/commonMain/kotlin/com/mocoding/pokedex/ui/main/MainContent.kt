package com.mocoding.pokedex.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun MainContent(component: MainComponent) {

    val state by component.state.collectAsState(MainState())

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
            if (state.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error?.let { error ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = error)
                }
            }

            LazyColumn {
                items(state.pokemonList) { pokemon ->
                    TextButton(
                        onClick = {
                            component.onOutput(MainComponent.Output.Selected(pokemon.name))
                        }
                    ) {
                        Text(text = pokemon.name)
                    }
                }
            }
        }
    }

}