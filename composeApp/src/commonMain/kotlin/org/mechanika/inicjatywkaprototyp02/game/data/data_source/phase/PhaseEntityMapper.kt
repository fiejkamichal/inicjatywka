package org.mechanika.inicjatywkaprototyp02.game.data.data_source.phase

import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.phasedatabase.PhaseEntity

fun PhaseEntity.toPhase(): Phase {
    return Phase(
        value = Phase.Phases.valueOf(this.value_)
    )
}