package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mechanika.inicjatywka.core.presentation.BottomSheet

@Composable
fun DebugBottomSheet(
    isOpen: Boolean,
    modifier: Modifier,
    viewModel: DebugViewModel
) {
    BottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { viewModel.onEvent(DebugEvent.OnDebugCloseClicked) }
                ) {
                    Text("Zamknij Debug")
                }
                Debug(
                    component = viewModel
                )
            }

        }
    }
}