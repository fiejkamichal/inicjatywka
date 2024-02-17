package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDao
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class EngineRepositoryImpl(
    private val dao: EngineDao
) : EngineRepository {
    override fun getPhase(): Flow<Phase?> {
        return dao.getPhase()
    }

    override fun setPhase(phase: Phase): Phase {
        dao.setPhase(phase)
        return phase
    }

    override fun getPhases(): Flow<List<Phase>> {
        return dao.getPhases()
    }

    override fun getCurrentCardId(): Long? {
        return dao.getCurrentCardId()
    }

    override fun getCurrentCardIdAsFlow(): Flow<Long?> {
        return dao.getCurrentCardIdAsFlow()
    }

    override fun setCurrentCardId(cardId: Long): Long {
        dao.setCurrentCardId(cardId)
        return cardId
    }

    override fun unsetCurrentCardId() {
        dao.deleteCurrentCardId()
    }

    override fun getCurrentCardIds(): Flow<List<Long>> {
        return dao.getCurrentCardIds()
    }
}