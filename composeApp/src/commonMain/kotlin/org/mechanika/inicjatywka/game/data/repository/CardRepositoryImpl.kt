package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.card.CardDao
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.repository.CardRepository

class CardRepositoryImpl(
    private val dao: CardDao
) : CardRepository {
    override fun insertCard(card: Card): Long {
        return dao.insertCard(card)
    }

    override fun insertDeletedCard(card: Card): Long {
        return dao.insertDeletedCard(card)
    }

    override fun updateCard(id: Long, card: Card) {
        return dao.updateCard(id, card)
    }

    override fun deleteCard(id: Long) {
        dao.deleteCard(id)
    }

    override fun markCardAsDeleted(id: Long) {
        dao.markCardAsDeleted(id)
    }

    override fun unmarkCardAsDeleted(id: Long) {
        dao.unmarkCardAsDeleted(id)
    }

    override fun getCardAsFlow(id: Long): Flow<Card?> {
        return dao.getCardAsFlow(id)
    }

    override fun getCard(id: Long): Card? {
        return dao.getCard(id)
    }

    override fun getCards(): Flow<List<Card>> {
        return dao.getCards()
    }

    override fun getDeletedCards(): Flow<List<Card>> {
        return dao.getDeletedCards()
    }
}