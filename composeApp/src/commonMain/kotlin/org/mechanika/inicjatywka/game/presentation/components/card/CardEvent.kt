package org.mechanika.inicjatywka.game.presentation.components.card

import org.mechanika.inicjatywka.game.domain.model.card.Card

sealed interface CardEvent {
    data class EditCard(val card: Card?) : CardEvent
    data class SaveCard(val card: Card) : CardEvent
    data class UpdateCardStat(val id: Card.Stat.Id, val value: String) :
        CardEvent

}