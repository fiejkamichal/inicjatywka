package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardUpdateAction
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


    fun insertCharacterCardAddAction(characterCardAdd: CharacterCardAddAction): Long
    fun deleteCharacterCardAddAction(actionId: Long): Long
    fun getCharacterCardAddAction(actionId: Long): CharacterCardAddAction?
    fun getCharacterCardAddActions(): Flow<List<CharacterCardAddAction>>


    fun insertCharacterCardDeleteAction(characterCardDelete: CharacterCardDeleteAction): Long
    fun deleteCharacterCardDeleteAction(actionId: Long)
    fun getCharacterCardDeleteAction(actionId: Long): CharacterCardDeleteAction?
    fun getCharacterCardDeleteActions(): Flow<List<CharacterCardDeleteAction>>
    fun getNumOfActionStackEntriesFlow(): Flow<Long>
    fun insertCharacterCardUpdateAction(characterCardUpdate: CharacterCardUpdateAction): Long
    fun deleteCharacterCardUpdateAction(actionId: Long)
    fun getCharacterCardUpdateAction(actionId: Long): CharacterCardUpdateAction?
    fun getCharacterCardUpdateActions(): Flow<List<CharacterCardUpdateAction>>
}