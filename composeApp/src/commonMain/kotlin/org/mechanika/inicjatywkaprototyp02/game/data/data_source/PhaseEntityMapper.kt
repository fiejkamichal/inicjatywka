package org.mechanika.inicjatywkaprototyp02.game.data.data_source

import database.PhaseEntity
import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase

fun PhaseEntity.toPhase(): Phase {
    return Phase(
        id = id,
        value = Phase.Phases.valueOf(this.value_)
    )
}