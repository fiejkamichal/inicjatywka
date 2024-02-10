package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDao
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class EngineRepositoryImpl(
    private val dao: EngineDao
) : EngineRepository {
    override fun getPhase(): Flow<Phase?> {
        return dao.getPhase()
    }

    override fun setPhase(phase: Phase): Phase {
        dao.setPhase(phase)
        return phase
    }

    override fun getPhases(): Flow<List<Phase>> {
        return dao.getPhases()
    }
}