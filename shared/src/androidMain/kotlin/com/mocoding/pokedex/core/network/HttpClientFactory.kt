package com.mocoding.pokedex.core.network

import io.ktor.client.*
import io.ktor.client.engine.android.Android

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Android)
}