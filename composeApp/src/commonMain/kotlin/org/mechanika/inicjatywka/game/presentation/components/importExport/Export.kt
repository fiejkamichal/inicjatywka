package org.mechanika.inicjatywka.game.presentation.components.importExport

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.darkrockstudios.libraries.mpfilepicker.DirectoryPicker


@Composable
fun Export(
    modifier: Modifier = Modifier,
    onExport: (path: String) -> Unit
) {
    var showDirPicker by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        Button(
            onClick = { showDirPicker = true }
        ) {
            Text("Export")
        }
    }

    DirectoryPicker(showDirPicker) { path ->
        showDirPicker = false
        path?.let { onExport(path) }
    }
}