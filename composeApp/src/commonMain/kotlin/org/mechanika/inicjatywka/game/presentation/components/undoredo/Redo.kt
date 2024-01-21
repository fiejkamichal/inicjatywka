package org.mechanika.inicjatywka.game.presentation.components.undoredo

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun Redo(
    undoRedoViewModel: UndoRedoViewModel
) {
    val enabled = undoRedoViewModel.state.redoEnabled.collectAsState(false)
    Button(
        onClick = { undoRedoViewModel.onEvent(UndoRedoEvent.Redo) },
        enabled = enabled.value
    ) {
        Text(
            text = "Redo"
        )
    }
}