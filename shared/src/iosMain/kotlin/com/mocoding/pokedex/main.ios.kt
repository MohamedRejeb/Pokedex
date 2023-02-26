package com.mocoding.pokedex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Application
import com.mocoding.pokedex.ui.ContentView
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController =
    Application("Pokedex") {
        Column {
            // To skip upper part of screen.
            Box(
                modifier = Modifier
                    .height(40.dp)
            )
            ContentView()
        }
    }
