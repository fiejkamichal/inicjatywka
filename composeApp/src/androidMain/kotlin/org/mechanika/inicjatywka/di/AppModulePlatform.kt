package org.mechanika.inicjatywka.di

import android.content.Context
import org.mechanika.inicjatywka.core.data.DatabaseDriverFactory
import org.mechanika.inicjatywka.database.InicjatywkaDatabase

actual class AppModulePlatform(
    private val context: Context
) {

    actual val db: InicjatywkaDatabase by lazy {

        InicjatywkaDatabase(
            driver = DatabaseDriverFactory(context).create()
        )
    }
}

