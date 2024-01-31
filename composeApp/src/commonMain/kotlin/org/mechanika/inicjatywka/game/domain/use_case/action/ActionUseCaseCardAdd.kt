package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class ActionUseCaseCardAdd(
    private val cardRepository: CardRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CardAddAction
        if (a != null) cardRepository.markCardAsDeleted(a.cardId)
    }

    override fun redo(action: Action) {
        val a = action as? CardAddAction
        if (a != null) cardRepository.unmarkCardAsDeleted(a.cardId)
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteCardAddAction(action.id!!)
        val a = action as? CardAddAction
        if (a != null) cardRepository.deleteCard(a.cardId)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertCardAddAction(action as CardAddAction)

    override fun get(id: Long): Action? =
        actionRepository.getCardAddAction(id)
}