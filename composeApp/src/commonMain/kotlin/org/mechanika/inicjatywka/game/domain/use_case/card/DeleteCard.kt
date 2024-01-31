package org.mechanika.inicjatywka.game.domain.use_case.card

import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class DeleteCard(
    private val repository: CardRepository,
    private val stack: Stack
) {
    operator fun invoke(id: Long) {
        repository.markCardAsDeleted(id)
        stack.pushActionAboveCurrentPosition(
            CardDeleteAction(
                cardId = id
            )
        )
    }
}