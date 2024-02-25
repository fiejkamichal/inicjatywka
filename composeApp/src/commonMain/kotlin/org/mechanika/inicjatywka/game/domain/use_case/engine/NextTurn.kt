package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class NextTurn(
    private val engineRepository: EngineRepository,
    private val cardRepository: CardRepository,
    private val nextRound: NextRound,
    private val stack: Stack
) {
    operator fun invoke() {
        val fromCardId = engineRepository.getCurrentCardId() ?: 0
        val toCardId = getNextCardId(fromCardId)
        if (toCardId == null) nextRound()
        else {
            engineRepository.setCurrentCardId(toCardId)
            stack.pushActionAboveCurrentPosition(
                NextTurnAction(
                    fromCardId = fromCardId,
                    toCardId = toCardId
                )
            )
        }
    }

    fun getNextCardId(cardId: Long): Long? {
        val cards = cardRepository.getCards()
        val iterator = cards.listIterator()
        while (iterator.hasNext()) {
            val card = iterator.next()
            if (card.id == cardId) break
        }
        if (iterator.hasNext()) {
            val card = iterator.next()
            return card.id
        }
        return null
    }
}