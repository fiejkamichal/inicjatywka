package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.phase

import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Stack

class StopInitiative(
    private val repository: PhaseRepository,
    private val stack: Stack
) {
    operator fun invoke() {
        changePhase(
            to = Phase.Phases.Initial,
            repository = repository,
        )
        stack.pushActionAboveCurrentPosition(
            PhaseChangeAction(
                from = Phase.Phases.Initiative,
                to = Phase.Phases.Initial
            )
        )
    }
}