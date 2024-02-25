package org.mechanika.inicjatywka.game.domain.model.action

data class ActionListItem(
    val actionListId: Long,
    val actionId: Long,
    val listPosition: Long,
    val actionType: Long
)
