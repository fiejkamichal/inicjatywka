package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.database.InicjatywkaDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:database.db")
        InicjatywkaDatabase.Schema.create(driver)
        return driver

    }
}