package org.mechanika.inicjatywka.game.domain.model.action

data class CardAddAction(
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CardAdd,
    override var id: Long? = null
) : Action
