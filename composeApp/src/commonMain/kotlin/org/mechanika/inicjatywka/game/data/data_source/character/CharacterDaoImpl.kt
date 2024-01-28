package org.mechanika.inicjatywka.game.data.data_source.character

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.characterdatabase.CharacterDatabase
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

class CharacterDaoImpl(
    db: CharacterDatabase
) : CharacterDao {
    private val queries = db.characterQueries
    override fun insertCharacterCard(card: CharacterCard): Long {
        return queries.transactionWithResult {
            queries.insertCharacterCard(
                name = card.getStat(CharacterCard.Stat.Id.Name).value,
                initiative = card.getStat(CharacterCard.Stat.Id.Initiative).value.toLong(),
                ally = if (card.getStat(CharacterCard.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
                hitPoints = card.getStat(CharacterCard.Stat.Id.HitPoints).value.toLong(),
                resilience = card.getStat(CharacterCard.Stat.Id.Resilience).value.toLong(),
                mana = card.getStat(CharacterCard.Stat.Id.Mana).value.toLong(),
                concentration = card.getStat(CharacterCard.Stat.Id.Concentration).value.toLong(),
                movePoints = card.getStat(CharacterCard.Stat.Id.MovePoints).value.toLong(),
                steps = card.getStat(CharacterCard.Stat.Id.Steps).value.toLong(),
                states = card.getStat(CharacterCard.Stat.Id.States).value
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun insertDeletedCharacterCard(card: CharacterCard): Long {
        return queries.transactionWithResult {
            queries.insertDeletedCharacterCard(
                name = card.getStat(CharacterCard.Stat.Id.Name).value,
                initiative = card.getStat(CharacterCard.Stat.Id.Initiative).value.toLong(),
                ally = if (card.getStat(CharacterCard.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
                hitPoints = card.getStat(CharacterCard.Stat.Id.HitPoints).value.toLong(),
                resilience = card.getStat(CharacterCard.Stat.Id.Resilience).value.toLong(),
                mana = card.getStat(CharacterCard.Stat.Id.Mana).value.toLong(),
                concentration = card.getStat(CharacterCard.Stat.Id.Concentration).value.toLong(),
                movePoints = card.getStat(CharacterCard.Stat.Id.MovePoints).value.toLong(),
                steps = card.getStat(CharacterCard.Stat.Id.Steps).value.toLong(),
                states = card.getStat(CharacterCard.Stat.Id.States).value
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun updateCharacterCard(id: Long, card: CharacterCard) {
        queries.updateCharacterCard(
            id = id,
            name = card.getStat(CharacterCard.Stat.Id.Name).value,
            initiative = card.getStat(CharacterCard.Stat.Id.Initiative).value.toLong(),
            ally = if (card.getStat(CharacterCard.Stat.Id.Ally).value.toBoolean()) 1L else 0L,
            hitPoints = card.getStat(CharacterCard.Stat.Id.HitPoints).value.toLong(),
            resilience = card.getStat(CharacterCard.Stat.Id.Resilience).value.toLong(),
            mana = card.getStat(CharacterCard.Stat.Id.Mana).value.toLong(),
            concentration = card.getStat(CharacterCard.Stat.Id.Concentration).value.toLong(),
            movePoints = card.getStat(CharacterCard.Stat.Id.MovePoints).value.toLong(),
            steps = card.getStat(CharacterCard.Stat.Id.Steps).value.toLong(),
            states = card.getStat(CharacterCard.Stat.Id.States).value,
            waits = if (card.getStat(CharacterCard.Stat.Id.Waits).value.toBoolean()) 1L else 0L
        )
    }

    override fun deleteCharacterCard(id: Long) {
        queries.deleteCharacterCard(id)
    }

    override fun markCharacterCardAsDeleted(id: Long) {
        queries.markCharacterCardAsDeleted(id)
    }

    override fun unmarkCharacterCardAsDeleted(id: Long) {
        queries.unmarkCharacterCardAsDeleted(id)
    }

    override fun getCharacterCardAsFlow(id: Long): Flow<CharacterCard?> {
        return queries.getCharacterCard(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map {
                it?.toCharacterCard()
            }
    }

    override fun getCharacterCard(id: Long): CharacterCard? {
        return queries.getCharacterCard(id)
            .executeAsOneOrNull()
            ?.toCharacterCard()
    }

    override fun getCharacterCards(): Flow<List<CharacterCard>> {
        return queries.getCharacterCards()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCharacterCard()
                }
            }
    }

    override fun getDeletedCharacterCards(): Flow<List<CharacterCard>> {
        return queries.getDeletedCharacterCards()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCharacterCard()
                }
            }
    }
}