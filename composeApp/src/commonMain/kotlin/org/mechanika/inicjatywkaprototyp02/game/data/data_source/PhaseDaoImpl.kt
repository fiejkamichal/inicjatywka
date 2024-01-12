package org.mechanika.inicjatywkaprototyp02.game.data.data_source

import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import org.mechanika.inicjatywkaprototyp02.database.PhaseDatabase
import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

class PhaseDaoImpl (
    db: PhaseDatabase
) : PhaseDao {
    private val queries = db.phaseQueries

    override suspend fun setPhase(phase: Phase) {
        queries.setPhaseEntity(phase.value.name)
    }

    override suspend fun getPhase(): Phase? {
        return queries.getPhaseEntity()
            .awaitAsOneOrNull()
            ?.toPhase()
    }
}