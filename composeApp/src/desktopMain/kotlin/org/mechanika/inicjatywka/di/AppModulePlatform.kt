package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.actiondatabase.ActionDatabase
import org.mechanika.inicjatywka.characterdatabase.CharacterDatabase
import org.mechanika.inicjatywka.core.data.ActionDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.CharacterDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.PhaseDatabaseDriverFactory
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.character.CharacterDao
import org.mechanika.inicjatywka.game.data.data_source.character.CharacterDaoImpl
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
    actual val characterDao: CharacterDao by lazy {
        CharacterDaoImpl(
            db = CharacterDatabase(
                driver = CharacterDatabaseDriverFactory().create()
            )
        )
    }
}

