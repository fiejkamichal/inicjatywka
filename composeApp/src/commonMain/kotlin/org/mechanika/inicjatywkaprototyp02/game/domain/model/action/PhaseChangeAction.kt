package org.mechanika.inicjatywkaprototyp02.game.domain.model.action

import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase

data class PhaseChangeAction (
    val id: Long? = null,
    val from: Phase.Phases,
    val to: Phase.Phases,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.PhaseChange
) : Action