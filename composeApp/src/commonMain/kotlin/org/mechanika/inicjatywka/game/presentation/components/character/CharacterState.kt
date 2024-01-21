package org.mechanika.inicjatywka.game.presentation.components.character

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

data class CharacterState(
    val cards: Flow<List<CharacterCard>>
)