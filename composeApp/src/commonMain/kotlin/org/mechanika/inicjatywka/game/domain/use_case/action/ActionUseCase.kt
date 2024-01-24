package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry

abstract class ActionUseCase {
    abstract fun undo(action: Action)
    abstract fun redo(action: Action)
    protected abstract fun deleteFromSubRepository(action: Action)
    fun delete (action: Action) {
        if (action.id != null) deleteFromSubRepository(action)
    }
    protected abstract fun insertToSubRepository(action: Action): Long
    fun insert(action: Action):Long? {
        action.id = insertToSubRepository(action)
        return action.id
    }
    abstract fun get(id: Long):Action?

    companion object {
        fun getActionUseCase(actions: Actions, type: ActionStackEntry.ActionTypes?): ActionUseCase {
            return when (type) {
                ActionStackEntry.ActionTypes.PhaseChange -> actions.actionUseCasePhaseChange
                ActionStackEntry.ActionTypes.CharacterCardAdd -> actions.actionUseCaseCharacterCardAdd
                ActionStackEntry.ActionTypes.CharacterCardDelete -> actions.actionUseCaseCharacterCardDelete
                null -> actions.actionUseCaseEmpty
            }
        }
    }
}

