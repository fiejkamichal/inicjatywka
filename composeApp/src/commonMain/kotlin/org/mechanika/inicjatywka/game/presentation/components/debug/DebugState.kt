package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard
import org.mechanika.inicjatywka.game.domain.model.phase.Phase

data class DebugState(
    val actions: Flow<List<ActionStackEntry>>,
    val phaseChanges: Flow<List<PhaseChangeAction>>,
    val currentPhase: Flow<Phase?>,
    val phases: Flow<List<Phase>>,
    val cards: Flow<List<CharacterCard>>,
    val deletedCards: Flow<List<CharacterCard>>,
    val currentStackPosition: MutableState<Long?>
)