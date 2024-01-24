package org.mechanika.inicjatywka.game.domain.use_case.action

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository

class Stack(
    private val repository: ActionRepository,
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
            if (action!=null) ActionUseCase
                .getActionUseCase(actions, action.type)
                .delete(action)
            repository.deleteActionStackEntry(pos)
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
        ActionUseCase
            .getActionUseCase(actions, action.type)
            .insert(action)
        repository.setActionStackEntry(position + 1, action.type, action.id!!)
        repository.setActionStackPosition(position + 1)
    }

    fun getActionOnCurrentPosition(): Action? {
        val position = getActionStackPosition()
        return getActionOnPosition(position)
    }
    private fun getActionOnPosition(position: Long): Action? {
        if (position == 0L) return null
        val entry = repository.getActionStackEntry(position) ?: return null
        return ActionUseCase
            .getActionUseCase(actions, entry.actionType)
            .get(entry.actionId)
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