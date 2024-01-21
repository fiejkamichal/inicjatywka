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
    override fun insertCharacterCard(card: CharacterCard):Long {
        return queries.transactionWithResult {
            queries.insertCharacterCard(
                name = card.name,
                initiative = card.initiative
            )
            queries.lastInsertRowId().executeAsOne()
        }
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

    override fun getCharacterCard(id: Long): Flow<CharacterCard?> {
        return queries.getCharacterCard(id)
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map {
                it?.toCharacterCard()
            }
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
            .map {list ->
                list.map {
                    it.toCharacterCard()
                }
            }
    }
}