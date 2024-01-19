package org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug

import androidx.compose.runtime.mutableStateOf
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.InicjatywkaUseCases

class DebugViewModel(
    inicjatywkaUseCases: InicjatywkaUseCases
) {
    val state = DebugState(
        actions = inicjatywkaUseCases.stack.getActions(),
        phaseChanges = inicjatywkaUseCases.debug.getChangePhases(),
        currentPhase = inicjatywkaUseCases.debug.getPhase(),
        phases = inicjatywkaUseCases.debug.getPhases(),
        currentStackPosition = mutableStateOf(inicjatywkaUseCases.debug.getCurrentStackPosition())
    )

    fun updateState() {
    }
}