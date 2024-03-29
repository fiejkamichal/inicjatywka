package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases

class DebugViewModel(
    inicjatywkaUseCases: InicjatywkaUseCases
) {
    val state = DebugState(
        actions = inicjatywkaUseCases.stack.getActions(),
        phaseChanges = inicjatywkaUseCases.debug.getChangePhases(),
        currentPhase = inicjatywkaUseCases.debug.getPhase(),
        phases = inicjatywkaUseCases.debug.getPhases(),
        cards = inicjatywkaUseCases.debug.getCards(),
        deletedCards = inicjatywkaUseCases.debug.getDeletedCards(),
        currentStackPosition = inicjatywkaUseCases.debug.getCurrentStackPosition(),
        currentCardId = inicjatywkaUseCases.debug.getCurrentCardId(),
        currentCardIds = inicjatywkaUseCases.debug.getCurrentCardIds(),
        actionListActionIds = inicjatywkaUseCases.debug.getActionListActionIds(),
        actionListItems = inicjatywkaUseCases.debug.getActionListItems(),
        allActions = inicjatywkaUseCases.debug.getAllActions()
    )

    var isDebugSheetOpen by mutableStateOf(DebugSheetState())
        private set

    fun onEvent(event: DebugEvent) {
        isDebugSheetOpen = when (event) {
            DebugEvent.OnDebugButtonClicked -> isDebugSheetOpen.copy(isDebugSheetOpen = true)

            DebugEvent.OnDebugCloseClicked -> isDebugSheetOpen.copy(isDebugSheetOpen = false)
        }
    }
}