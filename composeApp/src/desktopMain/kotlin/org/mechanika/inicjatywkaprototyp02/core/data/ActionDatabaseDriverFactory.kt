package org.mechanika.inicjatywkaprototyp02.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywkaprototyp02.actiondatabase.ActionDatabase

actual class ActionDatabaseDriverFactory {
    actual fun create(): SqlDriver {

        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:action.db")
        ActionDatabase.Schema.create(driver)
        return driver

    }
}