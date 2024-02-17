package org.mechanika.inicjatywka.game.data.data_source.engine

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.database.InicjatywkaDatabase
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

class EngineDaoImpl(
    db: InicjatywkaDatabase
) : EngineDao {
    val queries = db.engineQueries

    override fun setPhase(phase: Phase) {
        queries.setPhaseEntity(phase.value.name)
    }

    override fun getPhases(): Flow<List<Phase>> {
        return queries.getPhaseEntities()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toPhase()
                }
            }
    }

    override fun getPhase(): Flow<Phase?> {
        return queries.getPhaseEntity()
            .asFlow()
            .mapToOneOrNull(context = Dispatchers.IO)
            .map {
                it?.toPhase()
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
                list.map {
                    it.toLong()
                }
            }
    }

    override fun getCurrentCardIdAsFlow(): Flow<Long?> {
        return queries.getCurrentCardEntity()
            .asFlow()
            .mapToOneOrNull(context = Dispatchers.IO)
            .map {
                it?.toLong()
            }
    }

    override fun getCurrentCardId(): Long? {
        return queries.getCurrentCardEntity()
            .executeAsOneOrNull()?.toLong()
    }

}