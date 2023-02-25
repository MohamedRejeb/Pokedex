package com.mocoding.pokedex.core.network

import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf<HttpClient> { createHttpClient() }
}