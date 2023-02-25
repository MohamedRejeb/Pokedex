package com.mocoding.pokedex.core.network.errors

enum class PokedexError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class PokedexException(error: PokedexError): Exception(
    "Something goes wrong: $error"
)