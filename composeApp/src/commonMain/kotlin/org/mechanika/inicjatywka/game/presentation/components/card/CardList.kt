package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardList(
    cardListViewModel: CardListViewModel,
    cardEditViewModel: CardEditViewModel
) {
    val cards = cardListViewModel.state.cards.collectAsState(emptyList())
    val cardEdit = cardEditViewModel.cardEdit

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Karty Postaci (${cards.value.size})",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        cards.value.forEach { it ->
            if (cardEdit != null && it.id == cardEdit.id)
                CardEdit(
                    cardEdit = cardEditViewModel.cardEdit,
                    onUpdate = { id, value ->
                        cardEditViewModel.onEvent(CardEditEvent.UpdateCardStat(id, value))
                    },
                    onSave = { card ->
                        cardEditViewModel.onEvent(CardEditEvent.SaveCard(card))
                        cardEditViewModel.cardEdit = null
                    }
                )
            else
                Card(
                    card = it,
                    viewModel = cardListViewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            cardEditViewModel.onEvent(CardEditEvent.EditCard(it))
                        }
                )
        }
    }
}