package org.mechanika.inicjatywka.game.presentation.initial_phase

import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoEvent

sealed interface InitialPhaseEvent {
    data object StartInitiative: InitialPhaseEvent
    data class UndoRedo(val event: UndoRedoEvent): InitialPhaseEvent
}