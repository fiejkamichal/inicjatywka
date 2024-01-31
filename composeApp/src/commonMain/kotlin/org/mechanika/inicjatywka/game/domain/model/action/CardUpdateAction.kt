package org.mechanika.inicjatywka.game.domain.model.action

data class CardUpdateAction(
    val cardId: Long,
    val prevCardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CardUpdate,
    override var id: Long? = null
) : Action