package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.actiondatabase.ActionDatabase

actual class ActionDatabaseDriverFactory {
    actual fun create(): SqlDriver {

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:action.db")
        ActionDatabase.Schema.create(driver)
        return driver

    }
}