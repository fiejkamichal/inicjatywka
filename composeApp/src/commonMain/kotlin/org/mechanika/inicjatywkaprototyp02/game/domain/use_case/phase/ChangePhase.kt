package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.phase

import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

fun changePhase(
    to: Phase.Phases,
    repository: PhaseRepository,
){
    repository.setPhase(Phase(to))
}