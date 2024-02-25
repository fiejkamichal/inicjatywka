package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.NextRoundAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class ActionUseCaseNextRound(
    private val engineRepository: EngineRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? NextRoundAction
        if (a != null) {
            engineRepository.setCurrentCardId(a.fromCardId)
            engineRepository.setRound(a.fromRound)
            engineRepository.setReverse(a.fromReverse)
        }
    }

    override fun redo(action: Action) {
        val a = action as? NextRoundAction
        if (a != null) {
            engineRepository.setCurrentCardId(a.toCardId)
            engineRepository.setRound(a.toRound)
            engineRepository.setReverse(a.toReverse)
        }
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteNextRoundAction(action.id!!)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertNextRoundAction(action as NextRoundAction)

    override fun get(id: Long): Action? =
        actionRepository.getNextRoundAction(id)
}