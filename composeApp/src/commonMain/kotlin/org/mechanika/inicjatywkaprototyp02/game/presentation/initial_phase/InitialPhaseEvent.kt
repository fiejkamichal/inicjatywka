package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase

import org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo.UndoRedoEvent

sealed interface InitialPhaseEvent {
    data object StartInitiative: InitialPhaseEvent
    data class UndoRedo(val event: UndoRedoEvent): InitialPhaseEvent
}