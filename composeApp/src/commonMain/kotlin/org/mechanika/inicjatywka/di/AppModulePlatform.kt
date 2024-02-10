package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.data.data_source.card.CardDao
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDao


expect class AppModulePlatform {
    val engineDao: EngineDao
    val actionDao: ActionDao
    val cardDao: CardDao
}