package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.carddatabase.CardDatabase

actual class CardDatabaseDriverFactory {
    actual fun create(): SqlDriver {

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:card.db")
        CardDatabase.Schema.create(driver)
        return driver

    }
}