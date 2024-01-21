package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

@Composable
fun Character(
    card: CharacterCard,
    viewModel: CharacterViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(4.dp, Color.Black)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
        ) {
            Text("Karta postaci (${card.id}):")
            card.getStats().forEach {
                Stat(it)
            }
        }
        Button(
            onClick = { if (card.id != null) viewModel.onEvent(CharacterEvent.DeleteCharacter(card.id!!)) },
            enabled = card.id != null
        ) {
            Text("Usuń")
        }
    }
}

@Composable
fun Stat(
    stat: CharacterCard.Stat
) {
    Row {
        Text(
            text = stat.name + ": " + stat.value
        )
    }
}