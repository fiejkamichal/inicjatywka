package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry

interface ActionUseCase {
    fun undo(action: Action)
    fun redo(action: Action)
    fun delete(action: Action)
    fun insert(action: Action):Long?
    fun get(id: Long):Action?
}

class EmptyActionUseCase : ActionUseCase {
    override fun undo(action: Action) {}
    override fun redo(action: Action) {}
    override fun delete(action: Action) {}
    override fun insert(action: Action): Long? {return null}
    override fun get(id: Long): Action? {return null}
}

fun getActionUseCase(actions: Actions, type: ActionStackEntry.ActionTypes?): ActionUseCase {
    return when (type) {
        ActionStackEntry.ActionTypes.PhaseChange -> actions.phaseChangeActionUseCase
        ActionStackEntry.ActionTypes.CharacterCardAdd -> actions.characterCardAddActionUseCase
        ActionStackEntry.ActionTypes.CharacterCardDelete -> actions.characterCardDeleteActionUseCase
        null -> actions.emptyActionUseCase
    }
}