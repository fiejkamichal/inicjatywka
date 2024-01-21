package org.mechanika.inicjatywka.core.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.mechanika.inicjatywka.characterdatabase.CharacterDatabase

actual class CharacterDatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            CharacterDatabase.Schema,
            context = context,
            "character.db"
        )
    }
}