package org.mechanika.inicjatywka.game.domain.use_case.engine

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class GetCurrentCardId(
    private val repository: EngineRepository
) {
    operator fun invoke(): Flow<Long?> {
        return repository.getCurrentCardIdAsFlow()
    }
}