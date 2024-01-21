package org.mechanika.inicjatywka.game.data.data_source.action

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
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
    fun setActionStackPosition(position: Long)
    fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long
    fun deletePhaseChangeAction(actionId: Long)
    fun getPhaseChangeAction(actionId: Long): PhaseChangeAction?
    fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>>

    fun insertCharacterCardAddAction(cardId: Long): Long
    fun deleteCharacterCardAddAction(actionId: Long)
    fun getCharacterCardAddAction(actionId: Long): CharacterCardAddAction?
    fun getCharacterCardAddActions(): Flow<List<CharacterCardAddAction>>

    fun insertCharacterCardDeleteAction(cardId: Long): Long
    fun deleteCharacterCardDeleteAction(actionId: Long)
    fun getCharacterCardDeleteAction(actionId: Long): CharacterCardDeleteAction?
    fun getCharacterCardDeleteActions(): Flow<List<CharacterCardDeleteAction>>
}