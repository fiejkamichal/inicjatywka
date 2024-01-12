package org.mechanika.inicjatywkaprototyp02.game.data.repository

import org.mechanika.inicjatywkaprototyp02.game.data.data_source.PhaseDao
import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class PhaseRepositoryImpl(
    private val dao: PhaseDao
) : PhaseRepository {
    override suspend fun getPhase(): Phase? {
        return dao.getPhase()
    }

    override suspend fun setPhase(phase: Phase): Phase {
        dao.setPhase(phase)
        return phase
    }
}