package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class ActionUseCaseCharacterCardAdd(
    private val characterRepository: CharacterRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteCharacterCardAddAction(action.id!!)
        val a = action as? CharacterCardAddAction
        if (a != null) characterRepository.deleteCharacterCard(a.cardId)
    }

    override fun insertToSubRepository(action: Action):Long =
        actionRepository.insertCharacterCardAddAction(action as CharacterCardAddAction)
    override fun get(id: Long): Action? =
        actionRepository.getCharacterCardAddAction(id)
}