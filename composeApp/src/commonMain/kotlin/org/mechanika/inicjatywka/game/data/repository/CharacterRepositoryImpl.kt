package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.character.CharacterDao
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dao: CharacterDao
) : CharacterRepository {
    override fun insertCharacterCard(card: CharacterCard): Long {
        return dao.insertCharacterCard(card)
    }

    override fun insertDeletedCharacterCard(card: CharacterCard): Long {
        return dao.insertDeletedCharacterCard(card)
    }

    override fun updateCharacterCard(id: Long, card: CharacterCard) {
        return dao.updateCharacterCard(id, card)
    }

    override fun deleteCharacterCard(id: Long) {
        dao.deleteCharacterCard(id)
    }

    override fun markCharacterCardAsDeleted(id: Long) {
        dao.markCharacterCardAsDeleted(id)
    }

    override fun unmarkCharacterCardAsDeleted(id: Long) {
        dao.unmarkCharacterCardAsDeleted(id)
    }

    override fun getCharacterCardAsFlow(id: Long): Flow<CharacterCard?> {
        return dao.getCharacterCardAsFlow(id)
    }

    override fun getCharacterCard(id: Long): CharacterCard? {
        return dao.getCharacterCard(id)
    }

    override fun getCharacterCards(): Flow<List<CharacterCard>> {
        return dao.getCharacterCards()
    }

    override fun getDeletedCharacterCards(): Flow<List<CharacterCard>> {
        return dao.getDeletedCharacterCards()
    }
}