package org.mechanika.inicjatywka.game.presentation.components.undoredo

import kotlinx.coroutines.flow.Flow

data class UndoRedoState(
    val undoEnabled: Flow<Boolean>,
    val redoEnabled: Flow<Boolean>
)
