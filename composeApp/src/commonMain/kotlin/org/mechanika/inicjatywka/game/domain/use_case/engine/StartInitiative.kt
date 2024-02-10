package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class StartInitiative(
    private val repository: EngineRepository,
    private val stack: Stack
) {
    operator fun invoke() {
        changePhase(
            to = Phase.Phases.Initiative,
            repository = repository,
        )
        stack.pushActionAboveCurrentPosition(
            PhaseChangeAction(
                from = Phase.Phases.Initial,
                to = Phase.Phases.Initiative
            )
        )
    }
}