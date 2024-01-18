package org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo

import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Redo
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action.Undo

class UndoRedoViewModel(
    private val undo: Undo,
    private val redo: Redo
) {
    fun onEvent(event: UndoRedoEvent) {
        when (event) {
            UndoRedoEvent.Redo -> runBlocking { redo() }
            UndoRedoEvent.Undo -> runBlocking { undo() }
        }
    }
}