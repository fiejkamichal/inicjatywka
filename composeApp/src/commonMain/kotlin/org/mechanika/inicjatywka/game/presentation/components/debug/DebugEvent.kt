package org.mechanika.inicjatywka.game.presentation.components.debug

sealed interface DebugEvent {
    data object OnDebugButtonClicked : DebugEvent
    data object OnDebugCloseClicked : DebugEvent
}