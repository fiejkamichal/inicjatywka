package org.mechanika.inicjatywka.game.data.data_source.engine

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.enginedatabase.EngineDatabase
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

class EngineDaoImpl(
    db: EngineDatabase
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
}