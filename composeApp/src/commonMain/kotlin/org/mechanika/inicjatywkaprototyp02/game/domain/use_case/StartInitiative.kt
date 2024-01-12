package org.mechanika.inicjatywkaprototyp02.game.domain.use_case

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class StartInitiative(
    private val repository: PhaseRepository
) {
    suspend operator fun invoke(){
        repository.setPhase(Phase(Phase.Phases.Initiative, 0))
    }
}