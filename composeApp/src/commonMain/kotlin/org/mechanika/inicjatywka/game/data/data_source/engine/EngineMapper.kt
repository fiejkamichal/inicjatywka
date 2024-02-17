package org.mechanika.inicjatywka.game.data.data_source.engine

import org.mechanika.inicjatywka.database.CurrentCardEntity
import org.mechanika.inicjatywka.database.PhaseEntity
import org.mechanika.inicjatywka.game.domain.model.engine.Phase

fun PhaseEntity.toPhase(): Phase {
    return Phase(
        value = Phase.Phases.valueOf(this.value_)
    )
}

fun CurrentCardEntity.toLong(): Long {
    return this.cardId
}

