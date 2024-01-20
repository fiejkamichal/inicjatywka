package org.mechanika.inicjatywka.game.domain.use_case.phase

import org.mechanika.inicjatywka.game.domain.model.phase.Phase
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository

fun changePhase(
    to: Phase.Phases,
    repository: PhaseRepository,
){
    repository.setPhase(Phase(to))
}