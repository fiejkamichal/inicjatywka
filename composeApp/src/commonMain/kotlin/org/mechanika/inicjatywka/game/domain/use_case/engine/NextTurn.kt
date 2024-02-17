package org.mechanika.inicjatywka.game.domain.use_case.engine

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class NextTurn(
    private val repository: EngineRepository,
    private val cardRepository: CardRepository
) {
    operator fun invoke() {
        var cardId = repository.getCurrentCardId() ?: 0
        runBlocking {
            cardRepository.getCards().collectLatest {
                val iter =
                    it.sortedByDescending { it.getStat(Card.Stat.Id.Initiative).value.toLong() }
                        .listIterator()
                while (iter.hasNext()) {
                    val card = iter.next()
                    if (card.id == cardId) break
                }
                if (iter.hasNext()) {
                    val card = iter.next()
                    cardId = card.id ?: 0
                }
            }
        }
        repository.setCurrentCardId(cardId)
    }
}