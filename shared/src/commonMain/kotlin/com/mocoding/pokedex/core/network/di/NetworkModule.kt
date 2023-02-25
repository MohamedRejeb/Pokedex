package com.mocoding.pokedex.core.network.di

import com.mocoding.pokedex.core.network.client.PokemonClient
import com.mocoding.pokedex.core.network.createHttpClient
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf<HttpClient> { createHttpClient() }
    singleOf<PokemonClient> { PokemonClient() }
}