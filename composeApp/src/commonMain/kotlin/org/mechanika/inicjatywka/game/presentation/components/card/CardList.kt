package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    viewModel: CardViewModel
) {
    val cards = viewModel.state.cards.collectAsState(emptyList())
    val cardEdit = viewModel.cardEdit

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row {
            Text(
                text = "Karty Postaci (${cards.value.size})",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
        cards.value.forEach {
            if (cardEdit != null && it.id == cardEdit.id)
                CardEdit(viewModel)
            else
                Card(
                    card = it,
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable {
                            viewModel.onEvent(CardEvent.EditCard(it))
                        }
                )
        }
    }
}