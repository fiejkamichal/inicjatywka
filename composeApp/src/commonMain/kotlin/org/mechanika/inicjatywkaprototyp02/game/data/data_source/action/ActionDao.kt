package org.mechanika.inicjatywkaprototyp02.game.data.data_source.action

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction

interface ActionDao {
    fun getActionStackEntry(position: Long): ActionStackEntry?
    fun setActionStackEntry(
        position: Long,
        actionType: ActionStackEntry.ActionTypes,
        actionId: Long
    )
    fun deleteActionStackEntry(position: Long)
    fun getNumOfActionStackEntries(): Long
    fun getActionStackEntries(): Flow<List<ActionStackEntry>>
    fun getActionStackPosition(): Long?
    fun setActionStackPosition(position: Long)
    fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long
    fun deletePhaseChangeAction(actionId: Long)
    fun getPhaseChangeAction(actionId: Long): PhaseChangeAction?
    fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>>
}