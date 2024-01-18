package org.mechanika.inicjatywkaprototyp02.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase.PhaseDao
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class PhaseRepositoryImpl(
    private val dao: PhaseDao
) : PhaseRepository {
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