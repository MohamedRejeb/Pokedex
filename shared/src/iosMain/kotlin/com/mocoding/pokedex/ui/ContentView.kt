package com.mocoding.pokedex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mocoding.pokedex.ui.root.RootComponent
import com.mocoding.pokedex.ui.root.RootContent

@Composable
internal fun ContentView(
    component: RootComponent,
) {
    RootContent(component)
}