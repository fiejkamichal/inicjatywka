package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDao
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class EngineRepositoryImpl(
    private val dao: EngineDao
) : EngineRepository {

    init {
        dao.getEngine() ?: dao.setEngine(Engine.Phases.Initial)
    }

    override fun getPhase(): Flow<Engine.Phases?> {
        return dao.getPhase()
    }

    override fun setPhase(phase: Engine.Phases): Engine.Phases {
        dao.setPhase(phase)
        return phase
    }

    override fun getPhases(): Flow<List<Engine.Phases>> {
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

    override fun getCurrentCardIds(): Flow<List<Long?>> {
        return dao.getCurrentCardIds()
    }

    override fun getRound(): Long {
        return dao.getRound() ?: 0
    }

    override fun setRound(round: Long) {
        dao.setRound(round)
    }

    override fun getRoundAsFlow(): Flow<Long> {
        return dao.getRoundAsFlow().map {
            it ?: 0
        }
    }
}