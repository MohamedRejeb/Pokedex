package com.mocoding.pokedex.core.network.helper

import com.mocoding.pokedex.core.network.errors.PokedexError
import com.mocoding.pokedex.core.network.errors.PokedexException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.errors.IOException

suspend inline fun <reified T> handleErrors(response: () -> HttpResponse): T {
    val result = try {
        response()
    } catch(e: IOException) {
        throw PokedexException(PokedexError.ServiceUnavailable)
    }

    when(result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw PokedexException(PokedexError.ClientError)
        500 -> throw PokedexException(PokedexError.ServerError)
        else -> throw PokedexException(PokedexError.UnknownError)
    }

    return try {
        result.body()
    } catch(e: Exception) {
        throw PokedexException(PokedexError.ServerError)
    }
}