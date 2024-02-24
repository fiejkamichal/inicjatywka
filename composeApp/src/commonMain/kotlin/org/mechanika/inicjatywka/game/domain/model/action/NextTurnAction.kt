package org.mechanika.inicjatywka.game.domain.model.action

data class NextTurnAction(
    val fromCardId: Long,
    val toCardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.NextTurn,
    override var id: Long? = null
) : Action
