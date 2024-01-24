package org.mechanika.inicjatywka.game.domain.model.action

data class CharacterCardDeleteAction(
    override var id: Long? = null,
    val cardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CharacterCardDelete
) : Action