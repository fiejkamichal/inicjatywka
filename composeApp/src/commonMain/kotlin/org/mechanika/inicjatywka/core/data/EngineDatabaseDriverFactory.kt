package org.mechanika.inicjatywka.core.data

import app.cash.sqldelight.db.SqlDriver

expect class EngineDatabaseDriverFactory {
    fun create(): SqlDriver
}