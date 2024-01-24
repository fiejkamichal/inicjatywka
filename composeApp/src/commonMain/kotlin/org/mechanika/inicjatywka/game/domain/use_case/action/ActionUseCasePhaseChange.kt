package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywka.game.domain.use_case.phase.changePhase

class ActionUseCasePhaseChange(
    private val phaseRepository: PhaseRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null) changePhase(
            to = a.from,
            repository = phaseRepository
        )
    }

    override fun redo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null) changePhase(
            to = a.to,
            repository = phaseRepository
        )
    }

    override fun deleteFromSubRepository(action: Action) =
        actionRepository.deletePhaseChangeAction(action.id!!)

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertPhaseChangeAction(action as PhaseChangeAction)

    override fun get(id: Long): Action? =
        actionRepository.getPhaseChangeAction(id)
}