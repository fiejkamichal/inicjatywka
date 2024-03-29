package org.mechanika.inicjatywka.game.domain.use_case.card

import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class GetCard(
    private val repository: CardRepository
) {
    operator fun invoke(id: Long): Card? {
        return repository.getCard(id)
    }
}