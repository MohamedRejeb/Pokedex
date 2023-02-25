package com.mocoding.pokedex.core.network.client

import com.mocoding.pokedex.core.network.NetworkConstants
import com.mocoding.pokedex.core.network.helper.handleErrors
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonClient: KoinComponent {

    private val httpClient by inject<HttpClient>()

    suspend fun getPokemonList(
        limit: Int = 20,
        offset: Int = 0
    ): String {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.route) {
                url {
                    parameters.append("limit", limit.toString())
                    parameters.append("offset", offset.toString())
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getPokemonByName(
        name: String,
    ): String {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

}