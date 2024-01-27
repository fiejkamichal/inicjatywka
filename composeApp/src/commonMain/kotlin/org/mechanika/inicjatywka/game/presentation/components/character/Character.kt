package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

@Composable
fun Character(
    card: CharacterCard,
    viewModel: CharacterViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
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