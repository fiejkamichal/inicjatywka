package org.mechanika.inicjatywka.game.presentation.components.undoredo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun Undo(
    undoRedoViewModel: UndoRedoViewModel
) {
    val enabled = remember {undoRedoViewModel.state}
    Button (
        onClick = { undoRedoViewModel.onEvent(UndoRedoEvent.Undo) },
        enabled = enabled.value.undoEnabled
    ) {
        Text (
            text = "Undo"
        )
    }
}