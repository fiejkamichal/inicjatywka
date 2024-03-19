package org.mechanika.inicjatywka.game.presentation.components.card

import org.mechanika.inicjatywka.game.domain.model.card.Card

sealed interface CardEditEvent {
    data class EditCard(val card: Card) : CardEditEvent
    data object StopEditCard : CardEditEvent
    data class SaveCard(val card: Card) : CardEditEvent
    data class UpdateCardStat(val id: Card.Stat.Id, val value: String) :
        CardEditEvent

}