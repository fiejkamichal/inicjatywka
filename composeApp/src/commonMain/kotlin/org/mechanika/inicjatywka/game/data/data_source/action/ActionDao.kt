package org.mechanika.inicjatywka.game.data.data_source.action

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction

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
    fun getActionStackPositionFlow(): Flow<Long>
    fun setActionStackPosition(position: Long)
    fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long
    fun deletePhaseChangeAction(actionId: Long)
    fun getPhaseChangeAction(actionId: Long): PhaseChangeAction?
    fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>>

    fun insertCardAddAction(cardId: Long): Long
    fun deleteCardAddAction(actionId: Long)
    fun getCardAddAction(actionId: Long): CardAddAction?
    fun getCardAddActions(): Flow<List<CardAddAction>>

    fun insertCardDeleteAction(cardId: Long): Long
    fun deleteCardDeleteAction(actionId: Long)
    fun getCardDeleteAction(actionId: Long): CardDeleteAction?
    fun getCardDeleteActions(): Flow<List<CardDeleteAction>>
    fun getNumOfActionStackEntriesFlow(): Flow<Long>
    fun insertCardUpdateAction(cardId: Long, prevCardId: Long): Long
    fun deleteCardUpdateAction(actionId: Long)
    fun getCardUpdateAction(actionId: Long): CardUpdateAction?
    fun getCardUpdateActions(): Flow<List<CardUpdateAction>>
}