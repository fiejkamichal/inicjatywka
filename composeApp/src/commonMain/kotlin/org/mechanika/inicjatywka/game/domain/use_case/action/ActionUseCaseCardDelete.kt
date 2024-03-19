package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class ActionUseCaseCardDelete(
    private val cardRepository: CardRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CardDeleteAction
        if (a != null) cardRepository.unmarkCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CardDeleteAction
        if (a != null) cardRepository.markCardAsDeleted(a.cardId)
    }

    override fun deleteFromSubRepository(action: Action) =
        actionRepository.deleteCardDeleteAction(action.id!!)

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertCardDeleteAction(action as CardDeleteAction)

    override fun get(id: Long): Action? =
        actionRepository.getCardDeleteAction(id)
}