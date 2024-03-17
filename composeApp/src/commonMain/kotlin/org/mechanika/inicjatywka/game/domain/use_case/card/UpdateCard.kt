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
        update(cardId, newCard)?.let {
            stack.pushActionAboveCurrentPosition(it)
        }
    }

    fun update(cardId: Long, newCard: Card): CardUpdateAction? {
        val card = repository.getCard(cardId)
        if (card != null && !(card.sameStats(newCard) && card.waits == newCard.waits)) {
            val prevId = repository.insertDeletedCard(card)
            repository.updateCard(cardId, newCard)
            return CardUpdateAction(
                cardId = cardId,
                prevCardId = prevId
            )
        }
        return null
    }
}