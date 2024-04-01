package org.mechanika.inicjatywka.game.presentation.initial_phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

data class InitialPhaseState(
    val currentPhase: Flow<Engine.Phases>,
    val cards: Flow<List<Card>>
)