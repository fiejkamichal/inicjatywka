package org.mechanika.inicjatywka.game.data.data_source.character

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

interface CharacterDao {

    fun insertCharacterCard(card: CharacterCard): Long
    fun deleteCharacterCard(id: Long)
    fun markCharacterCardAsDeleted(id: Long)
    fun unmarkCharacterCardAsDeleted(id: Long)
    fun getCharacterCard(id: Long): Flow<CharacterCard?>
    fun getCharacterCards(): Flow<List<CharacterCard>>
    fun getDeletedCharacterCards(): Flow<List<CharacterCard>>
}