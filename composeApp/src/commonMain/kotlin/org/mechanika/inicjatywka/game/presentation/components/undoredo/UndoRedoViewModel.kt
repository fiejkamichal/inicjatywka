package org.mechanika.inicjatywka.game.presentation.components.undoredo

import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo

class UndoRedoViewModel(
    private val undo: Undo,
    private val redo: Redo,
    stack: Stack
) {
    val state = UndoRedoState(
        undoEnabled = stack.canMovePositionDown(),
        redoEnabled = stack.canMovePositionUp()
    )

    fun onEvent(event: UndoRedoEvent) {
        when (event) {
            UndoRedoEvent.Redo -> redo()
            UndoRedoEvent.Undo -> undo()
        }
    }
}