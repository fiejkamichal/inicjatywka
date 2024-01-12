package org.mechanika.inicjatywkaprototyp02.game.domain.use_case

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class StopInitiative(
    private val repository: PhaseRepository
) {
    suspend operator fun invoke() {
        repository.setPhase(Phase(Phase.Phases.Initial,0))
    }
}