package com.mocoding.pokedex.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.mocoding.pokedex.ui.details.DetailsScreen
import com.mocoding.pokedex.ui.main.MainScreen
import com.mocoding.pokedex.ui.pokedex.PokedexScreen

@Composable
internal fun RootContent(component: RootComponent) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade()),
    ) {
        it
        when(val child = it.instance) {
            is RootComponent.Child.Main -> MainScreen(child.component)
            is RootComponent.Child.Pokedex -> PokedexScreen(child.component)
            is RootComponent.Child.Details -> DetailsScreen(child.component)
        }
    }
}