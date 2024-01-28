package org.mechanika.inicjatywka.game.presentation.components.debug

sealed interface DebugEvent {
    data object onDebugButtonClicked : DebugEvent
    data object onDebugCloseClicked : DebugEvent
}