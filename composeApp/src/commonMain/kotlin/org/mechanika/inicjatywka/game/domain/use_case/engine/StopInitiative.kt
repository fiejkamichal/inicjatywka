package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard

class StopInitiative(
    private val engineRepository: EngineRepository,
    private val cardRepository: CardRepository,
    private val updateCard: UpdateCard,
    private val stack: Stack
) {
    operator fun invoke() {
        val cardId = engineRepository.getCurrentCardId()
        val round = engineRepository.getRound()
        val reverse = engineRepository.getReverse()
        changePhase(
            to = Engine.Phases.Initial,
            cardId = null,
            repository = engineRepository,
            round = 0,
            reverse = false
        )

        val actions = mutableListOf<Action>()

        val cards = cardRepository.getCards()
        cards.forEach { card ->
            if (card.getStat(Card.Stat.Id.Waits).value.toBoolean()) {
                val cardNew = card.copy()
                cardNew.setStat(Card.Stat.Id.Waits, false.toString())
                card.id?.let { cardId ->
                    updateCard.update(cardId, cardNew)?.let {
                        actions.add(it)
                    }
                }
            }
        }

        actions.add(
            PhaseChangeAction(
                from = Engine.Phases.Initiative,
                to = Engine.Phases.Initial,
                cardId = cardId,
                round = round,
                reverse = reverse
            )
        )

        stack.pushActionAboveCurrentPosition(
            ActionListAction(
                actions = actions
            )
        )
    }
}