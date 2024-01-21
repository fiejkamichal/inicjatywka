package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.characterdatabase.CharacterDatabase

actual class CharacterDatabaseDriverFactory {
    actual fun create(): SqlDriver {

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:character.db")
        CharacterDatabase.Schema.create(driver)
        return driver

    }
}