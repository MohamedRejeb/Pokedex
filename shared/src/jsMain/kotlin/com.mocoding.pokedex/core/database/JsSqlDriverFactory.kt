package com.mocoding.pokedex.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import kotlinx.coroutines.await
import org.koin.core.scope.Scope


actual suspend fun Scope.sqlDriverFactory(): SqlDriver {
    return initSqlDriver(PokemonDatabase.Schema).await()
}