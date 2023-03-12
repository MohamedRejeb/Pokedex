package com.mocoding.pokedex.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Java)
}