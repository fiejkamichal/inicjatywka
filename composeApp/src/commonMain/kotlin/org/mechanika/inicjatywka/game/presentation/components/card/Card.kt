package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.mechanika.inicjatywka.game.domain.model.card.Card

@Composable
fun Card(
    card: Card,
    viewModel: CardListViewModel,
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
        Delete(
            card.id != null
        ) { if (card.id != null) viewModel.onEvent(CardListEvent.DeleteCard(card.id!!)) }
    }
}

@Composable
fun Stat(
    stat: Card.Stat
) {
    Row {
        Text(
            text = stat.name + ": " + stat.value
        )
    }
}