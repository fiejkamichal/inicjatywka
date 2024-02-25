package org.mechanika.inicjatywka.game.presentation.initiative_phase

sealed interface InitiativePhaseEvent {
    data object StopInitiative : InitiativePhaseEvent
    data object NextTurn : InitiativePhaseEvent
    data object Wait : InitiativePhaseEvent
}