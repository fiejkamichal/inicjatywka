package org.mechanika.inicjatywka.game.domain.use_case.engine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class GetPhase(
    private val repository: EngineRepository
) {
    operator fun invoke(): Flow<Phase.Phases> {
        return repository.getPhase()
            .map {
                it?.value ?: repository.setPhase(Phase(Phase.Phases.Initial)).value
            }
    }
}