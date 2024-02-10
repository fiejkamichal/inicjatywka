package org.mechanika.inicjatywka.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.mechanika.inicjatywka.enginedatabase.EngineDatabase

actual class EngineDatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            EngineDatabase.Schema,
            context = context,
            "engine.db"
        )
    }
}