package com.mocoding.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.mocoding.pokedex.core.di.initKoin
import com.mocoding.pokedex.ui.ContentView
import com.mocoding.pokedex.ui.root.RootComponent
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val koin = initKoin().koin

    val lifecycle = LifecycleRegistry()

    val rootComponent =
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            storeFactory = DefaultStoreFactory(),
            pokemonRepository = koin.get()
        )

    return Application("Pokedex") {
        Column {
            // To skip upper part of screen.
            Box(
                modifier = Modifier
                    .height(40.dp)
            )
            ContentView(component = rootComponent)
        }
    }
}
