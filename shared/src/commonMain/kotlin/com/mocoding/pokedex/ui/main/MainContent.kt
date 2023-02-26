package com.mocoding.pokedex.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(component: MainComponent) {

    val state by component.state.collectAsState(MainState())

    Scaffold {  paddingValue ->
        Surface(modifier = Modifier.padding(paddingValue)) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }

            state.error?.let { error ->
                Text(text = error)
            }

            LazyColumn {
                items(state.pokemonList) { pokemon ->
                    Text(text = pokemon.name)
                }
            }
        }
    }

}