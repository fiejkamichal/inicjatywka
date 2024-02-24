package org.mechanika.inicjatywka.game.domain.use_case.engine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class GetPhase(
    private val repository: EngineRepository
) {
    operator fun invoke(): Flow<Engine.Phases> {
        return repository.getPhase()
            .map {
                it ?: repository.setPhase(Engine.Phases.Initial)
            }
    }
}