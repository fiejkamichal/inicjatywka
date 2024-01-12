package org.mechanika.inicjatywkaprototyp02.game.domain.use_case

import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class GetPhase (
    private val repository: PhaseRepository
) {
    suspend operator fun invoke(): Phase.Phases {
        return repository.getPhase()?.value ?: repository.setPhase(Phase(Phase.Phases.Initial,0)).value
    }
}