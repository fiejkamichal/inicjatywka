package org.mechanika.inicjatywka.game.data.data_source.phase

import org.mechanika.inicjatywka.game.domain.model.phase.Phase
import org.mechanika.inicjatywka.phasedatabase.PhaseEntity

fun PhaseEntity.toPhase(): Phase {
    return Phase(
        value = Phase.Phases.valueOf(this.value_)
    )
}