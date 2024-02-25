package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class NextTurn(
    private val engineRepository: EngineRepository,
    private val cardRepository: CardRepository,
    private val nextRound: NextRound,
    private val updateCard: UpdateCard,
    private val stack: Stack
) {
    operator fun invoke() {
        val fromCardId = engineRepository.getCurrentCardId() ?: error("current card not found")
        val card = cardRepository.getCard(fromCardId) ?: error("card not found")
        val fromReverse = engineRepository.getReverse()
        var toReverse = fromReverse

        if (!fromReverse) {
            var toCardId = getNextCardId(fromCardId)
            if (toCardId == null) {
                toCardId = getNextWaitingCardId(fromCardId)
                if (toCardId == null) {
                    nextRound()
                    return
                } else {
                    toReverse = true
                    engineRepository.setReverse(toReverse)
                }
            }

            engineRepository.setCurrentCardId(toCardId)

            stack.pushActionAboveCurrentPosition(
                NextTurnAction(
                    fromCardId = fromCardId,
                    toCardId = toCardId,
                    fromReverse = fromReverse,
                    toReverse = toReverse
                )
            )

        } else {
            val toCardId = getNextWaitingCardId(fromCardId)
            if (toCardId == null) {
                nextRound()
                return
            }

            val actions = mutableListOf<Action>()
            if (card.getStat(Card.Stat.Id.Waits).value.toBoolean()) {
                val cardNew = card.copy()
                cardNew.setStat(Card.Stat.Id.Waits, false.toString())
                val prevCardId = updateCard.update(fromCardId, cardNew)
                prevCardId?.let {
                    actions.add(
                        CardUpdateAction(
                            cardId = fromCardId,
                            prevCardId = prevCardId
                        )
                    )
                }
            }

            engineRepository.setCurrentCardId(toCardId)

            actions.add(
                NextTurnAction(
                    fromCardId = fromCardId,
                    toCardId = toCardId,
                    fromReverse = fromReverse,
                    toReverse = toReverse
                )
            )

            stack.pushActionAboveCurrentPosition(
                ActionListAction(
                    actions = actions
                )
            )

        }

    }

    fun getNextCardId(cardId: Long): Long? {
        val cards = cardRepository.getCards()
        val iterator = cards.listIterator()
        while (iterator.hasNext()) {
            val card = iterator.next()
            if (card.id == cardId) break
        }
        if (iterator.hasNext()) {
            val card = iterator.next()
            return card.id
        }
        return null
    }

    private fun getNextWaitingCardId(cardId: Long): Long? {
        val cards = cardRepository.getCards()
        val iterator = cards.listIterator(cards.size)
        while (iterator.hasPrevious()) {
            val card = iterator.previous()
            if (card.id == cardId) break
        }
        while (iterator.hasPrevious()) {
            val card = iterator.previous()
            if (card.getStat(Card.Stat.Id.Waits).value.toBoolean()) return card.id
        }
        return null
    }

}