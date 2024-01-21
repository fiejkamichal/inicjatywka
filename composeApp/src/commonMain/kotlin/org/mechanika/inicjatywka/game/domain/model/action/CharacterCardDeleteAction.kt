package org.mechanika.inicjatywka.game.domain.model.action

data class CharacterCardDeleteAction(
    val id: Long? = null,
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CharacterCardDelete
) : Action