package org.mechanika.inicjatywka.game.domain.use_case.character

import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack

class DeleteCharacterCard(
    private val repository: CharacterRepository,
    private val stack: Stack
) {
    operator fun invoke(id: Long) {
        repository.markCharacterCardAsDeleted(id)
        stack.pushActionAboveCurrentPosition(
            CharacterCardDeleteAction(
                cardId = id
            )
        )
    }
}