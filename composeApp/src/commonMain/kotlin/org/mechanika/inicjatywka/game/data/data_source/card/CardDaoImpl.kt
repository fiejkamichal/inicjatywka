package org.mechanika.inicjatywka.game.data.data_source.card

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.carddatabase.CardDatabase
import org.mechanika.inicjatywka.game.domain.model.card.Card

class CardDaoImpl(
    db: CardDatabase
) : CardDao {
    private val queries = db.cardQueries
    override fun insertCard(card: Card): Long {
        return queries.transactionWithResult {
            queries.insertCard(
                name = card.getStat(Card.Stat.Id.Name).value,
                initiative = card.getStat(Card.Stat.Id.Initiative).value.toLong(),
                ally = if (card.getStat(Card.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
                hitPoints = card.getStat(Card.Stat.Id.HitPoints).value.toLong(),
                resilience = card.getStat(Card.Stat.Id.Resilience).value.toLong(),
                mana = card.getStat(Card.Stat.Id.Mana).value.toLong(),
                concentration = card.getStat(Card.Stat.Id.Concentration).value.toLong(),
                movePoints = card.getStat(Card.Stat.Id.MovePoints).value.toLong(),
                steps = card.getStat(Card.Stat.Id.Steps).value.toLong(),
                states = card.getStat(Card.Stat.Id.States).value
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun insertDeletedCard(card: Card): Long {
        return queries.transactionWithResult {
            queries.insertDeletedCard(
                name = card.getStat(Card.Stat.Id.Name).value,
                initiative = card.getStat(Card.Stat.Id.Initiative).value.toLong(),
                ally = if (card.getStat(Card.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
                hitPoints = card.getStat(Card.Stat.Id.HitPoints).value.toLong(),
                resilience = card.getStat(Card.Stat.Id.Resilience).value.toLong(),
                mana = card.getStat(Card.Stat.Id.Mana).value.toLong(),
                concentration = card.getStat(Card.Stat.Id.Concentration).value.toLong(),
                movePoints = card.getStat(Card.Stat.Id.MovePoints).value.toLong(),
                steps = card.getStat(Card.Stat.Id.Steps).value.toLong(),
                states = card.getStat(Card.Stat.Id.States).value
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun updateCard(id: Long, card: Card) {
        queries.updateCard(
            id = id,
            name = card.getStat(Card.Stat.Id.Name).value,
            initiative = card.getStat(Card.Stat.Id.Initiative).value.toLong(),
            ally = if (card.getStat(Card.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
            hitPoints = card.getStat(Card.Stat.Id.HitPoints).value.toLong(),
            resilience = card.getStat(Card.Stat.Id.Resilience).value.toLong(),
            mana = card.getStat(Card.Stat.Id.Mana).value.toLong(),
            concentration = card.getStat(Card.Stat.Id.Concentration).value.toLong(),
            movePoints = card.getStat(Card.Stat.Id.MovePoints).value.toLong(),
            steps = card.getStat(Card.Stat.Id.Steps).value.toLong(),
            states = card.getStat(Card.Stat.Id.States).value,
            waits = if (card.getStat(Card.Stat.Id.Waits).value.toBoolean()) 1L else 0L
        )
    }

    override fun deleteCard(id: Long) {
        queries.deleteCard(id)
    }

    override fun markCardAsDeleted(id: Long) {
        queries.markCardAsDeleted(id)
    }

    override fun unmarkCardAsDeleted(id: Long) {
        queries.unmarkCardAsDeleted(id)
    }

    override fun getCardAsFlow(id: Long): Flow<Card?> {
        return queries.getCard(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map {
                it?.toCard()
            }
    }

    override fun getCard(id: Long): Card? {
        return queries.getCard(id)
            .executeAsOneOrNull()
            ?.toCard()
    }

    override fun getCards(): Flow<List<Card>> {
        return queries.getCards()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCard()
                }
            }
    }

    override fun getDeletedCards(): Flow<List<Card>> {
        return queries.getDeletedCards()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCard()
                }
            }
    }
}