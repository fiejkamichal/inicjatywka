package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun New(
    characterViewModel: CharacterViewModel
) {
    Button(
        onClick = { characterViewModel.onEvent(CharacterEvent.NewCharacter) },
    ) {
        Text(
            text = "Dodaj Postać"
        )
    }
}