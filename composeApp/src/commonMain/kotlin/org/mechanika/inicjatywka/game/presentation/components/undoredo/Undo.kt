package org.mechanika.inicjatywka.game.presentation.components.undoredo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun Undo(
    undoRedoViewModel: UndoRedoViewModel
) {
    val enabled = undoRedoViewModel.state.undoEnabled.collectAsState(false)
    Button(
        onClick = { undoRedoViewModel.onEvent(UndoRedoEvent.Undo) },
        enabled = enabled.value
    ) {
        Text(
            text = "Undo"
        )
    }
}