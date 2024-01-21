package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterCardDeleteActionUseCase (
    private val repository: CharacterRepository
):ActionUseCase {
    override fun undo(action: Action?) {
        val a = action as CharacterCardDeleteAction
        repository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action?) {
        val a = action as CharacterCardDeleteAction
        repository.markCharacterCardAsDeleted(a.cardId)
    }
}