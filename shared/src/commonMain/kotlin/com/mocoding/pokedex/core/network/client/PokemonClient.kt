package com.mocoding.pokedex.core.network.client

import com.mocoding.pokedex.core.model.PokemonInfo
import com.mocoding.pokedex.core.network.NetworkConstants
import com.mocoding.pokedex.core.network.helper.handleErrors
import com.mocoding.pokedex.core.network.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonClient: KoinComponent {

    private val httpClient by inject<HttpClient>()

    suspend fun getPokemonList(
        page: Int,
    ): PokemonResponse {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.route) {
                url {
                    parameters.append("limit", PageSize.toString())
                    parameters.append("offset", (page * PageSize).toString())
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getPokemonByName(
        name: String,
    ): PokemonInfo {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val PageSize = 20
    }

}