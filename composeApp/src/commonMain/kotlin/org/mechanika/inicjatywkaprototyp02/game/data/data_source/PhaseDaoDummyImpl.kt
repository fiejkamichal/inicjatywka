package org.mechanika.inicjatywkaprototyp02.game.data.data_source

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

class PhaseDaoDummyImpl : PhaseDao {

    override suspend fun setPhase(phase: Phase) {

    }

    override suspend fun getPhase(): Phase? {
        return null
    }
}