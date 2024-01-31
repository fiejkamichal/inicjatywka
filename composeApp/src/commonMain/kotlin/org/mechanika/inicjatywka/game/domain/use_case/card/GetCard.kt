package org.mechanika.inicjatywka.game.domain.use_case.card

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class GetCard(
    private val repository: CardRepository
) {
    operator fun invoke(id: Long): Flow<Card?> {
        return repository.getCardAsFlow(id)
    }
}