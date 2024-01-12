package org.mechanika.inicjatywkaprototyp02.di

import org.mechanika.inicjatywkaprototyp02.game.data.data_source.PhaseDao


expect class AppModulePlatform {
    val phaseDao: PhaseDao
}