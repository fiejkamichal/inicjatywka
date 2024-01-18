package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

data class InitiativePhaseState(
    val currentPhase: Flow<Phase.Phases>
)