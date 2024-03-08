package org.mechanika.inicjatywka.game.presentation.components.card

sealed interface CardListEvent {
    data object NewCard : CardListEvent
    data class DeleteCard(val id: Long) : CardListEvent
}