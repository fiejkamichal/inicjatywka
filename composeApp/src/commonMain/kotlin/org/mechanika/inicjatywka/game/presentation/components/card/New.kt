package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun New(
    cardViewModel: CardViewModel
) {
    Button(
        onClick = { cardViewModel.onEvent(CardEvent.NewCard) },
    ) {
        Text(
            text = "Dodaj Postać"
        )
    }
}