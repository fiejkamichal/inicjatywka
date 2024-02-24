package org.mechanika.inicjatywka.game.domain.model.action

data class NextRoundAction(
    val fromCardId: Long,
    val toCardId: Long,
    val fromRound: Long,
    val toRound: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.NextRound,
    override var id: Long? = null
) : Action
