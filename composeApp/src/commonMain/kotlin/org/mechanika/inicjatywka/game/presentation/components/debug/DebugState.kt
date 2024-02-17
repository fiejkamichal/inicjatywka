package org.mechanika.inicjatywka.game.presentation.components.debug

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

data class DebugState(
    val actions: Flow<List<ActionStackEntry>>,
    val phaseChanges: Flow<List<PhaseChangeAction>>,
    val currentPhase: Flow<Phase?>,
    val phases: Flow<List<Phase>>,
    val cards: Flow<List<Card>>,
    val deletedCards: Flow<List<Card>>,
    val currentStackPosition: Flow<Long?>,
    val currentCardId: Flow<Long?>,
    val currentCardIds: Flow<List<Long>>
)

data class DebugSheetState(
    val isDebugSheetOpen: Boolean = false
)