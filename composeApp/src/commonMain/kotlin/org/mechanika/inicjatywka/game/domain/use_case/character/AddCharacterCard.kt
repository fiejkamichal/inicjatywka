package org.mechanika.inicjatywka.game.domain.use_case.character

import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class AddCharacterCard(
    private val repository: CharacterRepository,
    private val stack: Stack
) {
    operator fun invoke(card: CharacterCard) {
        val id = repository.insertCharacterCard(card)
        card.id = id
        stack.pushActionAboveCurrentPosition(
            CharacterCardAddAction(
                cardId = id
            )
        )
    }
}