package org.mechanika.inicjatywka.game.domain.model.action

import org.mechanika.inicjatywka.game.domain.model.engine.Engine

data class PhaseChangeAction(
    override var id: Long? = null,
    val from: Engine.Phases,
    val to: Engine.Phases,
    val cardId: Long?,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.PhaseChange
) : Action