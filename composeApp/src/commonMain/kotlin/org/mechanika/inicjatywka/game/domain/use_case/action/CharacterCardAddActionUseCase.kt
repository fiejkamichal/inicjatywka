package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterCardAddActionUseCase(
    private val repository: CharacterRepository
):ActionUseCase {
    override fun undo(action: Action?) {
        val a = action as CharacterCardAddAction
        repository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action?) {
        val a = action as CharacterCardAddAction
        repository.unmarkCharacterCardAsDeleted(a.cardId)
    }
}