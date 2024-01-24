package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action

class EmptyActionUseCase : ActionUseCase() {
    override fun undo(action: Action) {}
    override fun redo(action: Action) {}
    override fun deleteFromSubRepository(action: Action) {}
    override fun insertToSubRepository(action: Action): Long {
        return 0
    }
    override fun get(id: Long): Action? {return null}
}