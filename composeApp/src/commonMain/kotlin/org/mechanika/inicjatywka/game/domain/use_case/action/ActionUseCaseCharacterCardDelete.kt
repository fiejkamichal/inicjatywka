package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class ActionUseCaseCharacterCardDelete(
    private val characterRepository: CharacterRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CharacterCardDeleteAction
        if (a != null) characterRepository.unmarkCharacterCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardDeleteAction
        if (a != null) characterRepository.markCharacterCardAsDeleted(a.cardId)
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteCharacterCardDeleteAction(action.id!!)
        val a = action as? CharacterCardDeleteAction
        if (a != null) characterRepository.deleteCharacterCard(a.cardId)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertCharacterCardDeleteAction(action as CharacterCardDeleteAction)
    override fun get(id: Long): Action? =
        actionRepository.getCharacterCardDeleteAction(id)
}