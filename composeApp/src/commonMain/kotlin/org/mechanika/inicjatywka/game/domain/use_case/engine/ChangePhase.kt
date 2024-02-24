package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

fun changePhase(
    to: Engine.Phases,
    cardId: Long?,
    repository: EngineRepository,
) {
    repository.setPhase(to)
    if (cardId != null) repository.setCurrentCardId(cardId)
    else repository.unsetCurrentCardId()
}