package org.mechanika.inicjatywkaprototyp02.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywkaprototyp02.database.PhaseDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:phase.db")
        PhaseDatabase.Schema.create(driver)
        return driver
    }
}