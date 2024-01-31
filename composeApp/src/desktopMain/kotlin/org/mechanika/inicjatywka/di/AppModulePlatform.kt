package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.actiondatabase.ActionDatabase
import org.mechanika.inicjatywka.carddatabase.CardDatabase
import org.mechanika.inicjatywka.core.data.ActionDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.CardDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.PhaseDatabaseDriverFactory
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.card.CardDao
import org.mechanika.inicjatywka.game.data.data_source.card.CardDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.phase.PhaseDao
import org.mechanika.inicjatywka.game.data.data_source.phase.PhaseDaoImpl
import org.mechanika.inicjatywka.phasedatabase.PhaseDatabase

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
    actual val cardDao: CardDao by lazy {
        CardDaoImpl(
            db = CardDatabase(
                driver = CardDatabaseDriverFactory().create()
            )
        )
    }
}

