package com.mocoding.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.mocoding.pokedex.core.di.initKoin
import com.mocoding.pokedex.ui.ContentView
import com.mocoding.pokedex.ui.root.RootComponent
import com.mocoding.pokedex.ui.theme.PokedexTheme
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

    return ComposeUIViewController {
        PokedexTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 50.dp)
            ) {
                ContentView(component = rootComponent)
            }
        }
    }
}
