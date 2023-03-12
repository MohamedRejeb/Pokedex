package com.mocoding.pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mocoding.pokedex.ui.root.RootComponent
import com.mocoding.pokedex.ui.root.RootContent
import com.mocoding.pokedex.ui.theme.PokedexTheme

@Composable
fun ContentView(component: RootComponent) {
    PokedexTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RootContent(component)
        }
    }
}