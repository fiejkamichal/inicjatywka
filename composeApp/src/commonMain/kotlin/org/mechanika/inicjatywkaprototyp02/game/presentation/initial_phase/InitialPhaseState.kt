package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

data class InitialPhaseState(
    val currentPhase: Phase.Phases = Phase.Phases.Initial
)