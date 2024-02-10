package org.mechanika.inicjatywka.game.domain.model.action

import org.mechanika.inicjatywka.game.domain.model.engine.Phase

data class PhaseChangeAction(
    override var id: Long? = null,
    val from: Phase.Phases,
    val to: Phase.Phases,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.PhaseChange
) : Action