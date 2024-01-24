package org.mechanika.inicjatywka.game.presentation.components.debug

import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases

class DebugViewModel(
    inicjatywkaUseCases: InicjatywkaUseCases
) {
    val state = DebugState(
        actions = inicjatywkaUseCases.stack.getActions(),
        phaseChanges = inicjatywkaUseCases.debug.getChangePhases(),
        currentPhase = inicjatywkaUseCases.debug.getPhase(),
        phases = inicjatywkaUseCases.debug.getPhases(),
        cards = inicjatywkaUseCases.debug.getCharacterCards(),
        deletedCards = inicjatywkaUseCases.debug.getDeletedCharacterCards(),
        currentStackPosition = inicjatywkaUseCases.debug.getCurrentStackPosition()
    )

    fun updateState() {
    }
}