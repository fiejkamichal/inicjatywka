package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.data.data_source.character.CharacterDao
import org.mechanika.inicjatywka.game.data.data_source.phase.PhaseDao


expect class AppModulePlatform {
    val phaseDao: PhaseDao
    val actionDao: ActionDao
    val characterDao: CharacterDao
}