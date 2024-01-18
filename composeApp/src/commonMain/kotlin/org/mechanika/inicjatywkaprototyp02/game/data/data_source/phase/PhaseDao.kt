package org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

interface PhaseDao {

    fun getPhase(): Flow<Phase?>
    fun setPhase(phase: Phase)
    fun getPhases(): Flow<List<Phase>>
}