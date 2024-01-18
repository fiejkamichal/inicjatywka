package org.mechanika.inicjatywkaprototyp02.di

import org.mechanika.inicjatywkaprototyp02.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase.PhaseDao


expect class AppModulePlatform {
    val phaseDao: PhaseDao
    val actionDao: ActionDao
}