package org.mechanika.inicjatywka

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.presentation.components.card.Card

@Preview
@Composable
fun Preview() {
    val card = Card(
        id = null
    )
    Card(
        card = card,
        onSave = { _ -> },
        onUpdate = { _, _ -> },
        onDelete = { _ -> }
    )
}

