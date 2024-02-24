package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class ActionUseCaseNextTurn(
    private val engineRepository: EngineRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? NextTurnAction
        if (a != null) {
            engineRepository.setCurrentCardId(a.fromCardId)
        }
    }

    override fun redo(action: Action) {
        val a = action as? NextTurnAction
        if (a != null) {
            engineRepository.setCurrentCardId(a.toCardId)
        }
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteNextTurnAction(action.id!!)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertNextTurnAction(action as NextTurnAction)

    override fun get(id: Long): Action? =
        actionRepository.getNextTurnAction(id)
}