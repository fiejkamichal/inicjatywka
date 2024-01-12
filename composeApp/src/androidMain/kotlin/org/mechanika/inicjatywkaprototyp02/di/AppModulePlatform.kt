package org.mechanika.inicjatywkaprototyp02.di

import android.content.Context
import org.mechanika.inicjatywkaprototyp02.core.data.DatabaseDriverFactory
import org.mechanika.inicjatywkaprototyp02.database.PhaseDatabase
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.PhaseDao
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.PhaseDaoDummyImpl
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.PhaseDaoImpl

actual class AppModulePlatform(
    private val context: Context
) {
    actual val phaseDao: PhaseDao by lazy {

        PhaseDaoImpl(
            db = PhaseDatabase(
                driver = DatabaseDriverFactory(context).create()
            )
        )


        //PhaseDaoDummyImpl()
    }
}

