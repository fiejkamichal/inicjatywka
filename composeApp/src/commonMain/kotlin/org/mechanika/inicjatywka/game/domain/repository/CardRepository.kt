package org.mechanika.inicjatywka.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.card.Card

interface CardRepository {
    fun insertCard(card: Card): Long
    fun deleteCard(id: Long)
    fun markCardAsDeleted(id: Long)
    fun unmarkCardAsDeleted(id: Long)
    fun getCardAsFlow(id: Long): Flow<Card?>
    fun getCards(): Flow<List<Card>>
    fun getDeletedCards(): Flow<List<Card>>
    fun insertDeletedCard(card: Card): Long
    fun updateCard(id: Long, card: Card)
    fun getCard(id: Long): Card?
}