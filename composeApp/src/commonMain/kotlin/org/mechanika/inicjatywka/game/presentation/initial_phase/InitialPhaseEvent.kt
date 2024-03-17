package org.mechanika.inicjatywka.game.presentation.initial_phase

sealed interface InitialPhaseEvent {
    data object StartInitiative : InitialPhaseEvent
    data class Import(val path: String) : InitialPhaseEvent
    data class Export(val path: String) : InitialPhaseEvent
}