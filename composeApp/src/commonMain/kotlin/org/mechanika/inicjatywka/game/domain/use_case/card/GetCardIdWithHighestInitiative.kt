package org.mechanika.inicjatywka.game.domain.use_case.card

import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class GetCardIdWithHighestInitiative(
    private val repository: CardRepository
) {
    operator fun invoke(): Long? {
        return repository.getCards().maxByOrNull { it.initiative }?.id
    }
}