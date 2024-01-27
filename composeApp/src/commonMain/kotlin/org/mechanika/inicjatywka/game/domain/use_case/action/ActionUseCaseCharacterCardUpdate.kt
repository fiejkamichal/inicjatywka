package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardUpdateAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class ActionUseCaseCharacterCardUpdate(
    private val characterRepository: CharacterRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CharacterCardUpdateAction
        if (a != null) {
            val card = characterRepository.getCharacterCard(a.cardId)
            val prevCard = characterRepository.getCharacterCard(a.prevCardId)
            if (card != null && prevCard != null) {
                characterRepository.updateCharacterCard(a.cardId, prevCard)
                characterRepository.updateCharacterCard(a.prevCardId, card)
            }
        }
    }

    override fun redo(action: Action) {
        val a = action as? CharacterCardUpdateAction
        if (a != null) {
            val card = characterRepository.getCharacterCard(a.cardId)
            val prevCard = characterRepository.getCharacterCard(a.prevCardId)
            if (card != null && prevCard != null) {
                characterRepository.updateCharacterCard(a.cardId, prevCard)
                characterRepository.updateCharacterCard(a.prevCardId, card)
            }
        }
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteCharacterCardUpdateAction(action.id!!)
        val a = action as? CharacterCardUpdateAction
        if (a != null) characterRepository.deleteCharacterCard(a.prevCardId)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertCharacterCardUpdateAction(action as CharacterCardUpdateAction)

    override fun get(id: Long): Action? =
        actionRepository.getCharacterCardUpdateAction(id)
}