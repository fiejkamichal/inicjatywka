package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.Action
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.ActionRepository

class Stack(
    val repository: ActionRepository
) {

    private fun getActionStackPosition(): Long {
        val position = repository.getActionStackPosition()
        if(position == null) {
            repository.setActionStackPosition(0L)
            return 0L
        }
        return position
    }

    private fun cleanStackAbovePosition(position: Long)
    {
        var pos = position + 1
        val entry = repository.getActionStackEntry(pos)
        while(entry != null)
        {
            repository.deleteActionStackEntry(pos)
            when(entry.actionType) {
                ActionStackEntry.ActionTypes.PhaseChange ->
                    repository.deletePhaseChangeAction(entry.actionId)
            }
            pos++
        }
    }

    private fun getActionStackHeight():Long {
        return repository.getNumOfActionStackEntries()
    }
    fun movePositionDown(): Long {
        val position = getActionStackPosition()
        if(position == 0L) return 0L
        return repository.setActionStackPosition(position - 1)
    }
    fun movePositionUp(): Long {
        val position = getActionStackPosition()
        val stackHeight = getActionStackHeight()
        return if(position < stackHeight)
            repository.setActionStackPosition(position + 1)
        else
            position
    }
    fun pushActionAboveCurrentPosition(
        action: Action
    ) {
        val position = getActionStackPosition()
        cleanStackAbovePosition(position)
        val actionId = when(action.type) {
            ActionStackEntry.ActionTypes.PhaseChange -> {
                repository.insertPhaseChangeAction(action as PhaseChangeAction)
            }
        }
        repository.setActionStackEntry(position+1, action.type, actionId)
        repository.setActionStackPosition(position+1)
    }

    fun getActionOnCurrentPosition(): Action?{
        val position = getActionStackPosition()
        if(position == 0L) return null
        val entry = repository.getActionStackEntry(position)
        return when(entry?.actionType) {
            ActionStackEntry.ActionTypes.PhaseChange -> {
                repository.getPhaseChangeAction(entry.actionId)
            }
            null -> null
        }
    }

    fun getActions(): Flow<List<ActionStackEntry>> {
        return repository.getActionStackEntries()
    }
}