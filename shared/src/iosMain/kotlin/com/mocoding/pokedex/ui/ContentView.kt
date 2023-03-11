package com.mocoding.pokedex.ui

import androidx.compose.runtime.Composable
import com.mocoding.pokedex.ui.root.RootComponent
import com.mocoding.pokedex.ui.root.RootContent

@Composable
internal fun ContentView(
    component: RootComponent,
) {
    RootContent(component)
}