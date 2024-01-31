package org.mechanika.inicjatywka.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.mechanika.inicjatywka.carddatabase.CardDatabase

actual class CardDatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            CardDatabase.Schema,
            context = context,
            "card.db"
        )
    }
}