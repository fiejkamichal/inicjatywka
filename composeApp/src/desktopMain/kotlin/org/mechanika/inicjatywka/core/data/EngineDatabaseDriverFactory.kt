package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.enginedatabase.EngineDatabase

actual class EngineDatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:engine.db")
        EngineDatabase.Schema.create(driver)
        return driver
    }
}