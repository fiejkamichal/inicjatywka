package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

fun changePhase(
    to: Phase.Phases,
    cardId: Long?,
    repository: EngineRepository,
) {
    repository.setPhase(Phase(to))
    if (cardId != null) repository.setCurrentCardId(cardId)
    else repository.unsetCurrentCardId()
}