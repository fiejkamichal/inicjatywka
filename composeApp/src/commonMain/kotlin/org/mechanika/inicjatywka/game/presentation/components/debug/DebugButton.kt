package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DebugButton(
    viewModel: DebugViewModel
) {
    Button(
        onClick = {
            viewModel.onEvent(DebugEvent.onDebugButtonClicked)
        }
    ) {
        Text("Debug")
    }
}