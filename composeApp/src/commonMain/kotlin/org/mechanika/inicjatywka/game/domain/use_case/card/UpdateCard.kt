package org.mechanika.inicjatywka.game.domain.use_case.card

import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class UpdateCard(
    private val repository: CardRepository,
    private val stack: Stack
) {
    operator fun invoke(cardId: Long, newCard: Card) {
        val prevId = update(cardId, newCard)
        if (prevId != null) {
            stack.pushActionAboveCurrentPosition(
                CardUpdateAction(
                    cardId = cardId,
                    prevCardId = prevId
                )
            )
        }
    }

    fun update(cardId: Long, newCard: Card): Long? {
        val card = repository.getCard(cardId)
        if (card != null && !card.sameStats(newCard)) {
            val prevId = repository.insertDeletedCard(card)
            repository.updateCard(cardId, newCard)
            return prevId
        }
        return null
    }
}