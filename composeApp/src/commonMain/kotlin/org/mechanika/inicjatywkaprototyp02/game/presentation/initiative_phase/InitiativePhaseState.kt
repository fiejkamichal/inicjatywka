package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

data class InitiativePhaseState(
    val currentPhase: Phase.Phases = Phase.Phases.Initiative
)