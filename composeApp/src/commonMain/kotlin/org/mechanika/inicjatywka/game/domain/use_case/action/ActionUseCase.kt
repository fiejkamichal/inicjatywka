package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry

interface ActionUseCase {
    fun undo(action: Action?)
    fun redo(action: Action?)
}

class EmptyActionUseCase: ActionUseCase {
    override fun undo(action: Action?) {}
    override fun redo(action: Action?) {}
}

fun getActionUseCase(actions: Actions, action: Action?): ActionUseCase {
    return when(action?.type) {
        ActionStackEntry.ActionTypes.PhaseChange -> actions.phaseChangeActionUseCase
        null -> actions.emptyActionUseCase
    }
}