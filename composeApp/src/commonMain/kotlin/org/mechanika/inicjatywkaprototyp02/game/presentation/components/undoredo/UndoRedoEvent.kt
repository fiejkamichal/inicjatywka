package org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo

sealed interface UndoRedoEvent {
    data object Undo: UndoRedoEvent
    data object Redo: UndoRedoEvent
}