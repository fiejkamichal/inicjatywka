package org.mechanika.inicjatywka.game.presentation.components.card

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.card.Card

data class CardState(
    val cards: Flow<List<Card>>
)