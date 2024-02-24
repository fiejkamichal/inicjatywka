package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.NextRoundAction
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCardIdWithHighestInitiative

class NextRound (
    private val engineRepository: EngineRepository,
    private val getCardIdWithHighestInitiative: GetCardIdWithHighestInitiative,
    private val stack: Stack
) {
    operator fun invoke(){
        val fromCardId = engineRepository.getCurrentCardId() ?: 0
        val toCardId = getCardIdWithHighestInitiative() ?: 0
        val fromRound = engineRepository.getRound()
        val toRound = fromRound + 1

        engineRepository.setCurrentCardId(toCardId)
        engineRepository.setRound(toRound)

        stack.pushActionAboveCurrentPosition(
            NextRoundAction(
                fromCardId = fromCardId,
                toCardId = toCardId,
                fromRound = fromRound,
                toRound = toRound
            )
        )

    }
}