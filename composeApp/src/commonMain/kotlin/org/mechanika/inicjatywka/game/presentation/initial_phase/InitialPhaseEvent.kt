package org.mechanika.inicjatywka.game.presentation.initial_phase

sealed interface InitialPhaseEvent {
    data object StartInitiative : InitialPhaseEvent
}