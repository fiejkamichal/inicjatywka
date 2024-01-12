package org.mechanika.inicjatywkaprototyp02.game.domain.repository

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

interface PhaseRepository {
    suspend fun getPhase(): Phase?

    suspend fun setPhase(phase: Phase): Phase
}