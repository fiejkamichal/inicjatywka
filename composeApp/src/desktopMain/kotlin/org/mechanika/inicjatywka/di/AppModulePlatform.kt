package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.actiondatabase.ActionDatabase
import org.mechanika.inicjatywka.carddatabase.CardDatabase
import org.mechanika.inicjatywka.core.data.ActionDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.CardDatabaseDriverFactory
import org.mechanika.inicjatywka.core.data.EngineDatabaseDriverFactory
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.card.CardDao
import org.mechanika.inicjatywka.game.data.data_source.card.CardDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDao
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDaoImpl
import org.mechanika.inicjatywka.enginedatabase.EngineDatabase

actual class AppModulePlatform {
    actual val engineDao: EngineDao by lazy {
        EngineDaoImpl(
            db = EngineDatabase(
                driver = EngineDatabaseDriverFactory().create()
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

