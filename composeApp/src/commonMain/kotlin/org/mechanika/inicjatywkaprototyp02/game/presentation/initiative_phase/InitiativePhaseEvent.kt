package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

sealed interface InitiativePhaseEvent {
    object StopInitiative: InitiativePhaseEvent
}