package com.mocoding.pokedex.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mocoding.pokedex.ui.main.components.MainContent

@Composable
internal fun MainScreen(component: MainComponent) {

    val state by component.state.collectAsState()

    MainContent(
        state = state,
        onEvent = component::onEvent,
        onOutput = component::onOutput,
    )

}