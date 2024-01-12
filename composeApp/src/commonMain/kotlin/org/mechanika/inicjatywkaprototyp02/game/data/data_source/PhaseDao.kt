package org.mechanika.inicjatywkaprototyp02.game.data.data_source

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

interface PhaseDao {

    suspend fun getPhase(): Phase?

    suspend fun setPhase(phase: Phase)
}