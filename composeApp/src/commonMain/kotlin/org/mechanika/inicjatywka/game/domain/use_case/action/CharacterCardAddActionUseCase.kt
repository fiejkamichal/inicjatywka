package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterCardAddActionUseCase(
    private val repository: CharacterRepository
) : ActionUseCase {
    override fun undo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) repository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) repository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun onDelete(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) repository.deleteCharacterCard(a.cardId)
    }
}