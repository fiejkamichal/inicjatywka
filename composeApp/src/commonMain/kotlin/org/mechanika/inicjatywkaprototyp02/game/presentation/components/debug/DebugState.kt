package org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

data class DebugState(
    val actions: Flow<List<ActionStackEntry>>,
    val phaseChanges: Flow<List<PhaseChangeAction>>,
    val currentPhase: Flow<Phase?>,
    val phases: Flow<List<Phase>>
)