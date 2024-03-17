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
import com.darkrockstudios.libraries.mpfilepicker.FilePicker

@Composable
fun Import(
    modifier: Modifier = Modifier,
    onImport: (path: String) -> Unit
) {
    var showFilePicker by remember { mutableStateOf(false) }

    val fileType = listOf("json")
    Box(
        modifier = modifier
    ) {
        Button(
            onClick = { showFilePicker = true }
        ) {
            Text("Import")
        }
    }

    FilePicker(show = showFilePicker, fileExtensions = fileType) { platformFile ->
        showFilePicker = false
        platformFile?.let { onImport(platformFile.path) }
    }
}