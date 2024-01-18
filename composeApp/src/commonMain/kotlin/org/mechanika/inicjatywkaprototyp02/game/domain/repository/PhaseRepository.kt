package org.mechanika.inicjatywkaprototyp02.game.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

interface PhaseRepository {
    fun getPhase(): Flow<Phase?>
    fun setPhase(phase: Phase): Phase
    fun getPhases(): Flow<List<Phase>>
}