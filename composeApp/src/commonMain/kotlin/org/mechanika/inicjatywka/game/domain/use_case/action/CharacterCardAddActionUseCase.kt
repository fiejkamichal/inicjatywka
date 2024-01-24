package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterCardAddActionUseCase(
    private val characterRepository: CharacterRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase {
    override fun undo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun delete(action: Action) {
        val actionId = action.id
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.deleteCharacterCard(a.cardId)
        if (actionId != null) actionRepository.deleteCharacterCardAddAction(actionId)
    }

    override fun insert(action: Action):Long {
        val actionId = actionRepository.insertCharacterCardAddAction(action as CharacterCardAddAction)
        action.id = actionId
        return actionId
    }

    override fun get(id: Long): Action? {
        return actionRepository.getCharacterCardAddAction(id)
    }
}