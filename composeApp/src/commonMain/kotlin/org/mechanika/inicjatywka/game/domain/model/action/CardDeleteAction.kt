package org.mechanika.inicjatywka.game.domain.model.action

data class CardDeleteAction(
    override var id: Long? = null,
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CardDelete
) : Action