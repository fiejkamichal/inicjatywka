package org.mechanika.inicjatywka.game.presentation.components.character

import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

sealed interface CharacterEvent {
    data object NewCharacter : CharacterEvent
    data class DeleteCharacter(val id: Long) : CharacterEvent
    data class EditCharacter(val character: CharacterCard) : CharacterEvent
    data class SaveCharacter(val character: CharacterCard) : CharacterEvent
    data class UpdateCharacterStat(val id: CharacterCard.Stat.Id, val value: String) :
        CharacterEvent
}