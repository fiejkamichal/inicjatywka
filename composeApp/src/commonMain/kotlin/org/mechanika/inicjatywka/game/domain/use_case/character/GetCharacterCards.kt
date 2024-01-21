package org.mechanika.inicjatywka.game.domain.use_case.character

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository

class GetCharacterCards(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<List<CharacterCard>> {
        return repository.getCharacterCards()
    }
}