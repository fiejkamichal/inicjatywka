package org.mechanika.inicjatywka.game.domain.model.action

data class ActionListAction(
    val actions: List<Action>,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.ActionList,
    override var id: Long? = null
) : Action
