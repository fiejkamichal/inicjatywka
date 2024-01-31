package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class ActionUseCaseCardUpdate(
    private val cardRepository: CardRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? CardUpdateAction
        if (a != null) {
            val card = cardRepository.getCard(a.cardId)
            val prevCard = cardRepository.getCard(a.prevCardId)
            if (card != null && prevCard != null) {
                cardRepository.updateCard(a.cardId, prevCard)
                cardRepository.updateCard(a.prevCardId, card)
            }
        }
    }

    override fun redo(action: Action) {
        val a = action as? CardUpdateAction
        if (a != null) {
            val card = cardRepository.getCard(a.cardId)
            val prevCard = cardRepository.getCard(a.prevCardId)
            if (card != null && prevCard != null) {
                cardRepository.updateCard(a.cardId, prevCard)
                cardRepository.updateCard(a.prevCardId, card)
            }
        }
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteCardUpdateAction(action.id!!)
        val a = action as? CardUpdateAction
        if (a != null) cardRepository.deleteCard(a.prevCardId)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertCardUpdateAction(action as CardUpdateAction)

    override fun get(id: Long): Action? =
        actionRepository.getCardUpdateAction(id)
}