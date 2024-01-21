package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywka.game.domain.use_case.phase.changePhase

class PhaseChangeActionUseCase(
    private val repository: PhaseRepository
) : ActionUseCase {
    override fun undo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null) changePhase(
            to = a.from,
            repository = repository
        )
    }

    override fun redo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null) changePhase(
            to = a.to,
            repository = repository
        )
    }

    override fun onDelete(action: Action) {

    }


}