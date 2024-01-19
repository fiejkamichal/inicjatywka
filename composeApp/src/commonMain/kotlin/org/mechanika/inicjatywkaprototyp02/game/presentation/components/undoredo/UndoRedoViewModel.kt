package org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Redo
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Stack
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Undo

class UndoRedoViewModel(
    private val undo: Undo,
    private val redo: Redo,
    private val stack: Stack
) {
    val state = mutableStateOf(UndoRedoState(
        undoEnabled = stack.canMovePositionDown(),
        redoEnabled = stack.canMovePositionUp()
    ))

    fun onEvent(event: UndoRedoEvent) {
        when (event) {
            UndoRedoEvent.Redo -> runBlocking {
                redo()
                state.value.copy(
                    undoEnabled = stack.canMovePositionDown(),
                    redoEnabled = stack.canMovePositionUp()
                )
            }
            UndoRedoEvent.Undo -> runBlocking {
                undo()
                state.value.copy(
                    undoEnabled = stack.canMovePositionDown(),
                    redoEnabled = stack.canMovePositionUp()
                )
            }
        }
    }
}