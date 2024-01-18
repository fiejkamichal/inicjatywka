package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action

import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.Action
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.phase.changePhase

class PhaseChangeActionUseCase(
    private val repository: PhaseRepository
): ActionUseCase {
    override fun undo(action: Action?) {
        val a = action as PhaseChangeAction
        changePhase(
            to = a.from,
            repository = repository
        )
    }

    override fun redo(action: Action?) {
        val a = action as PhaseChangeAction
        changePhase(
            to = a.to,
            repository = repository
        )
    }


}