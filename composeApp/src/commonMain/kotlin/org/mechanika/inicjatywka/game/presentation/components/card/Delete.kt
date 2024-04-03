package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Delete(
    enabled: Boolean,
    onDelete: () -> Unit
) {
    Button(
        onClick = onDelete,
        enabled = enabled
    ) {
        Text("Usuń")
    }
}