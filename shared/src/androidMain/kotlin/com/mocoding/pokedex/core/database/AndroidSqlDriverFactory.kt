package com.mocoding.pokedex.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {
    println("driver created")
    return AndroidSqliteDriver(PokemonDatabase.Schema, androidContext(), "${DatabaseConstants.name}.db")
}