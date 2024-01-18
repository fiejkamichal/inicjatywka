package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.phase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class GetPhase (
    private val repository: PhaseRepository
) {
    operator fun invoke(): Flow<Phase.Phases> {
        return repository.getPhase()
            .map {
                it?.value ?: repository.setPhase(Phase(Phase.Phases.Initial)).value
            }
    }
}