package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.core.data.DatabaseDriverFactory
import org.mechanika.inicjatywka.database.InicjatywkaDatabase

actual class AppModulePlatform {
    actual val db: InicjatywkaDatabase by lazy {
        InicjatywkaDatabase(
            driver = DatabaseDriverFactory().create()
        )

    }
}

