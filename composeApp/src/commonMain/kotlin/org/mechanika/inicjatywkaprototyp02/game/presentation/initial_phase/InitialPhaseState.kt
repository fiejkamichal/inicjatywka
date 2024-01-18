package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

data class InitialPhaseState(
    val currentPhase: Flow<Phase.Phases>
)