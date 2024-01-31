package org.mechanika.inicjatywka.game.domain.use_case.card

import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class AddCard(
    private val repository: CardRepository,
    private val stack: Stack
) {
    operator fun invoke(card: Card) {
        val id = repository.insertCard(card)
        card.id = id
        stack.pushActionAboveCurrentPosition(
            CardAddAction(
                cardId = id
            )
        )
    }
}