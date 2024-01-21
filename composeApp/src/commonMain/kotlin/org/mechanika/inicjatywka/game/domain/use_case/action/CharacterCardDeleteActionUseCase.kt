package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterCardDeleteActionUseCase(
    private val repository: CharacterRepository
) : ActionUseCase {
    override fun undo(action: Action) {
        val a = action as? CharacterCardDeleteAction
        if (a != null) repository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardDeleteAction
        if (a != null) repository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun onDelete(action: Action) {
        val a = action as? CharacterCardDeleteAction
        if (a != null) repository.deleteCharacterCard(a.cardId)
    }
}