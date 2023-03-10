package com.mocoding.pokedex.core.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

expect fun Scope.sqlDriverFactory(): SqlDriver
fun createDatabase(driver: SqlDriver): PokemonDatabase {
    val database = PokemonDatabase(
        driver = driver,
    )

    return database
}