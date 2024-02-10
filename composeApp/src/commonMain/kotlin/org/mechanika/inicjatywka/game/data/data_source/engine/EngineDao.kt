package org.mechanika.inicjatywka.game.data.data_source.engine

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

interface EngineDao {

    fun getPhase(): Flow<Phase?>
    fun setPhase(phase: Phase)
    fun getPhases(): Flow<List<Phase>>
}