package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class StopInitiative(
    private val repository: EngineRepository,
    private val stack: Stack
) {
    operator fun invoke() {
        val cardId = repository.getCurrentCardId()
        changePhase(
            to = Phase.Phases.Initial,
            cardId = null,
            repository = repository
        )
        stack.pushActionAboveCurrentPosition(
            PhaseChangeAction(
                from = Phase.Phases.Initiative,
                to = Phase.Phases.Initial,
                cardId = cardId
            )
        )
    }
}