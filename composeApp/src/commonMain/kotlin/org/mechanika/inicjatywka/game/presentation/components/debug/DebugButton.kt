package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DebugButton(
    viewModel: DebugViewModel
) {
    FloatingActionButton(
        onClick = {
            viewModel.onEvent(DebugEvent.OnDebugButtonClicked)
        }
    ) {
        Text("Debug")
    }
}