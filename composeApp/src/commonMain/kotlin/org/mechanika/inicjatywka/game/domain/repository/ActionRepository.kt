package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction

interface ActionRepository {
    fun getActionStackEntry(position: Long): ActionStackEntry?
    fun setActionStackEntry(position: Long, actionType: ActionStackEntry.ActionTypes, actionId: Long)
    fun deleteActionStackEntry(position: Long)
    fun getNumOfActionStackEntries(): Long
    fun getActionStackEntries(): Flow<List<ActionStackEntry>>


    fun getActionStackPosition(): Long?
    fun setActionStackPosition(position: Long): Long


    fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long
    fun deletePhaseChangeAction(actionId: Long)
    fun getPhaseChangeAction(actionId: Long): PhaseChangeAction?
    fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>>
}