package org.mechanika.inicjatywkaprototyp02.di

import org.mechanika.inicjatywkaprototyp02.actiondatabase.ActionDatabase
import org.mechanika.inicjatywkaprototyp02.core.data.ActionDatabaseDriverFactory
import org.mechanika.inicjatywkaprototyp02.core.data.PhaseDatabaseDriverFactory
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.action.ActionDaoImpl
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase.PhaseDao
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase.PhaseDaoImpl
import org.mechanika.inicjatywkaprototyp02.phasedatabase.PhaseDatabase

actual class AppModulePlatform {
    actual val phaseDao: PhaseDao by lazy {
        PhaseDaoImpl(
            db = PhaseDatabase(
                driver = PhaseDatabaseDriverFactory().create()
            )
        )
    }
    actual val actionDao: ActionDao by lazy {
        ActionDaoImpl(
            db = ActionDatabase(
                driver = ActionDatabaseDriverFactory().create()
            )
        )
    }
}

