package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.mechanika.inicjatywka.phasedatabase.PhaseDatabase

actual class PhaseDatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:phase.db")
        PhaseDatabase.Schema.create(driver)
        return driver
    }
}