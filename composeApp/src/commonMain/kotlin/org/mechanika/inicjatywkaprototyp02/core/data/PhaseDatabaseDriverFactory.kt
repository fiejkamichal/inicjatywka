package org.mechanika.inicjatywkaprototyp02.core.data

import app.cash.sqldelight.db.SqlDriver

expect class PhaseDatabaseDriverFactory {
    fun create(): SqlDriver
}