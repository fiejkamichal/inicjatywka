package org.mechanika.inicjatywka.game.domain.model.action

data class NextTurnAction(
    val fromCardId: Long,
    val toCardId: Long,
    val fromReverse: Boolean,
    val toReverse: Boolean,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.NextTurn,
    override var id: Long? = null
) : Action
