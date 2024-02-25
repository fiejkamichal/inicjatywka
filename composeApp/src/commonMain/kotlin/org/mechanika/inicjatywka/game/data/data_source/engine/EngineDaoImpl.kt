package org.mechanika.inicjatywka.game.data.data_source.engine

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.database.InicjatywkaDatabase
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

class EngineDaoImpl(
    db: InicjatywkaDatabase
) : EngineDao {
    private val queries = db.engineQueries

    override fun getEngine(): Engine? {
        return queries.getEngineEntity()
            .executeAsOneOrNull()?.toEngine()
    }

    override fun setEngine(phase: Engine.Phases) {
        return queries.setEngineEntity(phase.toString())
    }

    override fun setPhase(phase: Engine.Phases) {
        queries.setPhaseEntity(phase.name)
    }

    override fun getPhases(): Flow<List<Engine.Phases>> {
        return queries.getPhaseEntities()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    Engine.Phases.valueOf(it)
                }
            }
    }

    override fun getPhase(): Flow<Engine.Phases?> {
        return queries.getPhaseEntity()
            .asFlow()
            .mapToOneOrNull(context = Dispatchers.IO)
            .map {
                if (it != null) Engine.Phases.valueOf(it) else null
            }
    }

    override fun setCurrentCardId(cardId: Long) {
        queries.setCurrentCardEntity(cardId)
    }

    override fun deleteCurrentCardId() {
        queries.deleteCurrentCardEntity()
    }

    override fun getCurrentCardIds(): Flow<List<Long>> {
        return queries.getCurrentCardEntities()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.mapNotNull {
                    it.cardId
                }
            }
    }

    override fun getCurrentCardIdAsFlow(): Flow<Long?> {
        return queries.getCurrentCardEntity()
            .asFlow()
            .mapToOneOrNull(context = Dispatchers.IO)
            .map {
                it?.cardId
            }
    }

    override fun getCurrentCardId(): Long? {
        return queries.getCurrentCardEntity()
            .executeAsOneOrNull()?.cardId
    }

    override fun getRound(): Long? {
        return queries.getRoundEntity()
            .executeAsOneOrNull()?.round
    }

    override fun getRoundAsFlow(): Flow<Long?> {
        return queries.getRoundEntity()
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map {
                it?.round
            }
    }

    override fun setRound(round: Long?) {
        queries.setRoundEntity(round)
    }
}