package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver

expect class PhaseDatabaseDriverFactory {
    fun create(): SqlDriver
}