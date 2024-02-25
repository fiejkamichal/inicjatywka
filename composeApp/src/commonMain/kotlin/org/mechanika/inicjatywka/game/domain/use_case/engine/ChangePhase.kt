package org.mechanika.inicjatywka.game.domain.use_case.engine

import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

fun changePhase(
    to: Engine.Phases,
    cardId: Long?,
    round: Long,
    reverse: Boolean,
    repository: EngineRepository,
) {
    repository.setPhase(to)
    repository.setRound(round)
    repository.setReverse(reverse)
    if (cardId != null) repository.setCurrentCardId(cardId)
    else repository.unsetCurrentCardId()
}