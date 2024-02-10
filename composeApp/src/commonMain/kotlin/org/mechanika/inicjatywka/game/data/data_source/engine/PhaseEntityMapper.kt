package org.mechanika.inicjatywka.game.data.data_source.engine

import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.enginedatabase.PhaseEntity

fun PhaseEntity.toPhase(): Phase {
    return Phase(
        value = Phase.Phases.valueOf(this.value_)
    )
}