package org.mechanika.inicjatywka.game.presentation.components.undoredo

sealed interface UndoRedoEvent {
    data object Undo: UndoRedoEvent
    data object Redo: UndoRedoEvent
}