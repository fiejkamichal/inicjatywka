package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class Wait(
    private val engineRepository: EngineRepository,
    private val updateCard: UpdateCard,
    private val nextTurn: NextTurn,
    private val stack: Stack
) {
    operator fun invoke(card: Card) {
        val fromReverse = engineRepository.getReverse()
        if (fromReverse) return // cannot wait if this is reverse
        val cardId = card.id ?: return
        val nextCardId = nextTurn.getNextCardId(cardId)

        nextCardId ?: return //cannot wait if it is last

        card.waits = true
        val cardUpdateAction = updateCard.update(cardId, card)

        engineRepository.setCurrentCardId(nextCardId)

        stack.pushActionAboveCurrentPosition(
            ActionListAction(
                actions = listOfNotNull(
                    cardUpdateAction,
                    NextTurnAction(
                        fromCardId = cardId,
                        toCardId = nextCardId,
                        fromReverse = fromReverse,
                        toReverse = fromReverse
                    )
                )
            )
        )
    }
}