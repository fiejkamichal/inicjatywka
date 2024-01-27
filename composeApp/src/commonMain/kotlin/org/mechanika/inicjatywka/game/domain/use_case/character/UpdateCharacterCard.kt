package org.mechanika.inicjatywka.game.domain.use_case.character

import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class UpdateCharacterCard(
    private val repository: CharacterRepository,
    private val stack: Stack
) {
    operator fun invoke(cardId: Long, newCard: CharacterCard) {
        val card = repository.getCharacterCard(cardId)
        if (card != null) {
            val prevId = repository.insertDeletedCharacterCard(card)
            repository.updateCharacterCard(cardId, newCard)
            stack.pushActionAboveCurrentPosition(
                CharacterCardUpdateAction(
                    cardId = cardId,
                    prevCardId = prevId
                )
            )
        }
    }
}