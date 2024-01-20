package org.mechanika.inicjatywka.game.data.data_source.phase

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.phase.Phase

interface PhaseDao {

    fun getPhase(): Flow<Phase?>
    fun setPhase(phase: Phase)
    fun getPhases(): Flow<List<Phase>>
}