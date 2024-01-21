package org.mechanika.inicjatywka.game.domain.use_case.action

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository

class Stack(
    val repository: ActionRepository,
    private val actions: Actions
) {

    private fun getActionStackPosition(): Long {
        val position = repository.getActionStackPosition()
        if (position == null) {
            repository.setActionStackPosition(0L)
            return 0L
        }
        return position
    }

    private fun cleanStackAbovePosition(position: Long) {
        var pos = position + 1
        var entry = repository.getActionStackEntry(pos)
        while (entry != null) {
            val action = getActionOnPosition(pos)
            val actionUseCase = getActionUseCase(actions, action)
            if(action!=null)actionUseCase.onDelete(action)
            repository.deleteActionStackEntry(pos)
            when (entry.actionType) {
                ActionStackEntry.ActionTypes.PhaseChange ->
                    repository.deletePhaseChangeAction(entry.actionId)

                ActionStackEntry.ActionTypes.CharacterCardAdd ->
                    repository.deleteCharacterCardAddAction(entry.actionId)

                ActionStackEntry.ActionTypes.CharacterCardDelete ->
                    repository.deleteCharacterCardDeleteAction(entry.actionId)
            }
            pos++
            entry = repository.getActionStackEntry(pos)
        }
    }

    private fun getActionStackHeight(): Long {
        return repository.getNumOfActionStackEntries()
    }

    fun movePositionDown(): Long {
        val position = getActionStackPosition()
        if (position == 0L) return 0L
        return repository.setActionStackPosition(position - 1)
    }

    fun movePositionUp(): Long {
        val position = getActionStackPosition()
        val stackHeight = getActionStackHeight()
        return if (position < stackHeight)
            repository.setActionStackPosition(position + 1)
        else
            position
    }

    fun pushActionAboveCurrentPosition(
        action: Action
    ) {
        val position = getActionStackPosition()
        cleanStackAbovePosition(position)
        val actionId = when (action.type) {
            ActionStackEntry.ActionTypes.PhaseChange ->
                repository.insertPhaseChangeAction(action as PhaseChangeAction)

            ActionStackEntry.ActionTypes.CharacterCardAdd ->
                repository.insertCharacterCardAddAction(action as CharacterCardAddAction)

            ActionStackEntry.ActionTypes.CharacterCardDelete ->
                repository.insertCharacterCardDeleteAction(action as CharacterCardDeleteAction)
        }
        repository.setActionStackEntry(position + 1, action.type, actionId)
        repository.setActionStackPosition(position + 1)
    }

    fun getActionOnCurrentPosition(): Action? {
        val position = getActionStackPosition()
        return getActionOnPosition(position)
    }
    private fun getActionOnPosition(position: Long): Action? {
        if (position == 0L) return null
        val entry = repository.getActionStackEntry(position)
        return when (entry?.actionType) {
            ActionStackEntry.ActionTypes.PhaseChange ->
                repository.getPhaseChangeAction(entry.actionId)

            ActionStackEntry.ActionTypes.CharacterCardAdd ->
                repository.getCharacterCardAddAction(entry.actionId)

            ActionStackEntry.ActionTypes.CharacterCardDelete ->
                repository.getCharacterCardDeleteAction(entry.actionId)

            null -> null
        }
    }

    fun canMovePositionDown(): Flow<Boolean> {
        return repository.getActionStackPositionFlow()
            .map {
                it > 0
            }
    }

    fun canMovePositionUp(): Flow<Boolean> {
        return repository.getActionStackPositionFlow()
            .combine(repository.getNumOfActionStackEntriesFlow()) { position, stackHeight ->
                position < stackHeight
            }
    }

    fun getActions(): Flow<List<ActionStackEntry>> {
        return repository.getActionStackEntries()
    }
}