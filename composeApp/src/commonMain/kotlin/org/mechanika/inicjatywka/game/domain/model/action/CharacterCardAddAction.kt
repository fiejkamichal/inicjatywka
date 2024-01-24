package org.mechanika.inicjatywka.game.domain.model.action

data class CharacterCardAddAction(
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CharacterCardAdd,
    override var id: Long? = null
) : Action
