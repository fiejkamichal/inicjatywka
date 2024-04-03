package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Save(
    enabled: Boolean = true,
    onSave: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onSave,
    ) {
        Text("Zapisz")
    }
}