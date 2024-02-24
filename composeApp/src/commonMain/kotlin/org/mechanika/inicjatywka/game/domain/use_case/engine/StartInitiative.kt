package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCardIdWithHighestInitiative

class StartInitiative(
    private val repository: EngineRepository,
    private val getCardIdWithHighestInitiative: GetCardIdWithHighestInitiative,
    private val stack: Stack
) {
    operator fun invoke() {
        val cardId = getCardIdWithHighestInitiative()
        changePhase(
            to = Engine.Phases.Initiative,
            cardId = cardId,
            repository = repository
        )
        stack.pushActionAboveCurrentPosition(
            PhaseChangeAction(
                from = Engine.Phases.Initial,
                to = Engine.Phases.Initiative,
                cardId = cardId
            )
        )
    }
}