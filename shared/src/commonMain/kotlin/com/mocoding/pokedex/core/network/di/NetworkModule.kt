package com.mocoding.pokedex.core.network.di

import com.mocoding.pokedex.core.network.client.PokemonClient
import com.mocoding.pokedex.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: (enableLogging: Boolean) -> Module get() = { enableLogging ->
    module {
        single { createHttpClient(enableLogging) }
        single { PokemonClient(httpClient = get()) }
    }
}