package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun New(
    onNew: () -> Unit
) {
    Button(
        onClick = onNew,
    ) {
        Text(
            text = "Dodaj Postać"
        )
    }
}