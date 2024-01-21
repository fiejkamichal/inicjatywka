package org.mechanika.inicjatywka.game.domain.use_case.character

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class GetCharacterCard(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Long): Flow<CharacterCard?> {
        return repository.getCharacterCard(id)
    }
}