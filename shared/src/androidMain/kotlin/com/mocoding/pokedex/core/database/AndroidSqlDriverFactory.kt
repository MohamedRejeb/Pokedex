package com.mocoding.pokedex.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual suspend fun Scope.sqlDriverFactory(): SqlDriver {
    return AndroidSqliteDriver(PokemonDatabase.Schema, androidContext(), "${DatabaseConstants.name}.db")
}