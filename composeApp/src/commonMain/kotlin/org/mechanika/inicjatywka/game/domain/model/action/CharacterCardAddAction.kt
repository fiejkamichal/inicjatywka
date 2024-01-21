package org.mechanika.inicjatywka.game.domain.model.action

data class CharacterCardAddAction(
    val id: Long? = null,
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CharacterCardAdd
) : Action
