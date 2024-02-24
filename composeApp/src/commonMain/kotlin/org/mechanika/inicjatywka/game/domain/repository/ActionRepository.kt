package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction

interface ActionRepository {
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
    fun setActionStackPosition(position: Long): Long


    fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long
    fun deletePhaseChangeAction(actionId: Long)
    fun getPhaseChangeAction(actionId: Long): PhaseChangeAction?
    fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>>


    fun insertCardAddAction(cardAdd: CardAddAction): Long
    fun deleteCardAddAction(actionId: Long): Long
    fun getCardAddAction(actionId: Long): CardAddAction?
    fun getCardAddActions(): Flow<List<CardAddAction>>


    fun insertCardDeleteAction(cardDelete: CardDeleteAction): Long
    fun deleteCardDeleteAction(actionId: Long)
    fun getCardDeleteAction(actionId: Long): CardDeleteAction?
    fun getCardDeleteActions(): Flow<List<CardDeleteAction>>
    fun getNumOfActionStackEntriesFlow(): Flow<Long>
    fun insertCardUpdateAction(cardUpdate: CardUpdateAction): Long
    fun deleteCardUpdateAction(actionId: Long)
    fun getCardUpdateAction(actionId: Long): CardUpdateAction?
    fun getCardUpdateActions(): Flow<List<CardUpdateAction>>
    fun insertNextTurnAction(nextTurn: NextTurnAction): Long
    fun deleteNextTurnAction(actionId: Long)
    fun getNextTurnAction(actionId: Long): NextTurnAction?
    fun getNextTurnActions(): Flow<List<NextTurnAction>>
}