package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextRoundAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCardIdWithHighestInitiative
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class NextRound(
    private val engineRepository: EngineRepository,
    private val cardRepository: CardRepository,
    private val updateCard: UpdateCard,
    private val getCardIdWithHighestInitiative: GetCardIdWithHighestInitiative,
    private val stack: Stack
) {
    operator fun invoke() {
        val fromCardId = engineRepository.getCurrentCardId() ?: 0
        val toCardId = getCardIdWithHighestInitiative() ?: 0
        val fromRound = engineRepository.getRound()
        val toRound = fromRound + 1
        val fromReverse = engineRepository.getReverse()
        val toReverse = false

        engineRepository.setCurrentCardId(toCardId)
        engineRepository.setRound(toRound)
        engineRepository.setReverse(toReverse)


        val actions = mutableListOf<Action>()
        val card = cardRepository.getCard(fromCardId) ?: error("card not found")
        if (card.getStat(Card.Stat.Id.Waits).value.toBoolean()) {
            card.setStat(Card.Stat.Id.Waits, false.toString())
            val prevCardId = updateCard.update(fromCardId, card)
            prevCardId?.let {
                actions.add(
                    CardUpdateAction(
                        cardId = fromCardId,
                        prevCardId = prevCardId
                    )
                )
            }
        }

        actions.add(
            NextRoundAction(
                fromCardId = fromCardId,
                toCardId = toCardId,
                fromRound = fromRound,
                toRound = toRound,
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