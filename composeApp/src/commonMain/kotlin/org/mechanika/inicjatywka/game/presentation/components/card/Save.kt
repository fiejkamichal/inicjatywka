package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Save(
    onSave: () -> Unit
) {
    Button(
        onClick = onSave,
    ) {
        Text("Zapisz")
    }
}