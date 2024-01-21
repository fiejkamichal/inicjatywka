package org.mechanika.inicjatywka.game.presentation.components.character

sealed interface CharacterEvent {
    data object NewCharacter : CharacterEvent
    data class DeleteCharacter(val id: Long) : CharacterEvent
}