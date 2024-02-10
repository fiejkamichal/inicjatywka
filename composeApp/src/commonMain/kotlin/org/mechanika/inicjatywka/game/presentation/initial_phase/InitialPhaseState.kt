package org.mechanika.inicjatywka.game.presentation.initial_phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

data class InitialPhaseState(
    val currentPhase: Flow<Phase.Phases>
)