package com.mocoding.pokedex.core.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

internal fun createHttpClient(enableLogging: Boolean): HttpClient {
    return createPlatformHttpClient().config {
        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}