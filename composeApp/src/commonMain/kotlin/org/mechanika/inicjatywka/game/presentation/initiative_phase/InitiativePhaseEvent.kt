package org.mechanika.inicjatywka.game.presentation.initiative_phase

import org.mechanika.inicjatywka.game.domain.model.card.Card

sealed interface InitiativePhaseEvent {
    data object StopInitiative : InitiativePhaseEvent
    data object NextTurn : InitiativePhaseEvent
    data object Wait : InitiativePhaseEvent
    data class OnCardSelect(val card: Card) : InitiativePhaseEvent
    data class OnSelectedStatUpdate(val id: Card.Stat.Id, val value: String) : InitiativePhaseEvent
    data class OnCurrentStatUpdate(val id: Card.Stat.Id, val value: String) : InitiativePhaseEvent
    data class OnSelectedCardSave(val card: Card) : InitiativePhaseEvent
    data class OnCurrentCardSave(val card: Card) : InitiativePhaseEvent
    data object OnCardAdd : InitiativePhaseEvent
}