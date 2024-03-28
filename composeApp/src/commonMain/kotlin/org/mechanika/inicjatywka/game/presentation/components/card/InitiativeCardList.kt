package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.background
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
                    .background(
                        if (it.id == highlightedCardId)
                            if (it.getStat(Card.Stat.Id.Ally).value.toBoolean())
                                Color.Green
                            else
                                Color.Red
                        else
                            Color.Transparent
                    )
                    .border(
                        width = 1.dp,
                        color = if (it.waits)
                            Color.Blue
                        else Color.Black
                    )
                    .clickable {
                        onCardSelect(it)
                    }
                    .padding(5.dp),
            ) {
                Text(
                    text = it.getStat(Card.Stat.Id.Initiative).value,
                    fontWeight = (
                            if (it.id == highlightedCardId)
                                FontWeight.Bold
                            else
                                FontWeight.Light
                            ),
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.getStat(Card.Stat.Id.Name).value
                            + if (it.waits) " (czeka)" else "",
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