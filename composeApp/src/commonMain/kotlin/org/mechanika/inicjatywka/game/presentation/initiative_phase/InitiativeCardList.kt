package org.mechanika.inicjatywka.game.presentation.initiative_phase

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.card.Card

@Composable
fun InitiativeCardList(
    sortedCards: List<Card>,
    highlightedCardId: Long?,
    onCardSelect: (Card) -> Unit,
) {
    Column {
        sortedCards.forEach {
            Text(
                modifier = Modifier.border(
                    width = 1.dp,
                    color = if (it.getStat(Card.Stat.Id.Waits).value.toBoolean())
                        Color.Red
                    else Color.Black
                )
                    .clickable {
                        onCardSelect(it)
                    },
                text = it.getStat(Card.Stat.Id.Initiative).value + " " +
                        it.getStat(Card.Stat.Id.Name).value + " " + it.id.toString(),
                fontWeight = (
                        if (it.id == highlightedCardId)
                            FontWeight.Bold
                        else
                            FontWeight.Light
                        )
            )
        }
    }
}