package org.mechanika.inicjatywkaprototyp02.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

import org.mechanika.inicjatywkaprototyp02.actiondatabase.ActionDatabase

actual class ActionDatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            ActionDatabase.Schema,
            context = context,
            "action.db")
    }
}