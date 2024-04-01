package org.mechanika.inicjatywka.game.presentation.initial_phase

import org.mechanika.inicjatywka.game.domain.model.card.Card

sealed interface InitialPhaseEvent {
    data object StartInitiative : InitialPhaseEvent
    data class Import(val path: String) : InitialPhaseEvent
    data class Export(val path: String) : InitialPhaseEvent
    data class OnCardSelect(val card: Card) : InitialPhaseEvent
    data class OnStatUpdate(val id: Card.Stat.Id, val value: String) : InitialPhaseEvent
    data class OnCardSave(val card: Card) : InitialPhaseEvent
    data class OnCardDelete(val cardId: Long) : InitialPhaseEvent
    data object OnCardAdd : InitialPhaseEvent
}