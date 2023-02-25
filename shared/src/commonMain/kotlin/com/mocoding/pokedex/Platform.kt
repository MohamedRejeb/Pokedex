package com.mocoding.pokedex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform