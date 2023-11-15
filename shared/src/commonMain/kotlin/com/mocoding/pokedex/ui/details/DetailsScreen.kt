package com.mocoding.pokedex.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mocoding.pokedex.ui.details.components.DetailsContent

@Composable
internal fun DetailsScreen(component: DetailsComponent) {

    val state by component.state.collectAsState()

    DetailsContent(
        state = state,
        onEvent = component::onEvent,
        onOutput = component::onOutput
    )

}