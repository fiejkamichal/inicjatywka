package org.mechanika.inicjatywka.game.data.data_source.engine

import org.mechanika.inicjatywka.database.EngineEntity
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

fun EngineEntity.toEngine(): Engine {
    return Engine(
        phase = Engine.Phases.valueOf(this.phase),
        cardId = this.cardId
    )
}
