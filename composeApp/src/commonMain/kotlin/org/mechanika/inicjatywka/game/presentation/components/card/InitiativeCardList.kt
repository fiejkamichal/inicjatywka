package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = if (it.waits)
                            Color.Red
                        else Color.Black
                    )
                    .padding(1.dp)
                    .clickable {
                        onCardSelect(it)
                    },
            ) {
                Text (
                    text = it.getStat(Card.Stat.Id.Initiative).value,
                    fontWeight = (
                            if (it.id == highlightedCardId)
                                FontWeight.Bold
                            else
                                FontWeight.Light
                            ),
                    textAlign = TextAlign.Start
                )
                Text (
                    modifier = Modifier.fillMaxWidth(),
                    text = it.getStat(Card.Stat.Id.Name).value + " (" + it.id.toString() + ")",
                    fontWeight = (
                            if (it.id == highlightedCardId)
                                FontWeight.Bold
                            else
                                FontWeight.Light
                            ),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}