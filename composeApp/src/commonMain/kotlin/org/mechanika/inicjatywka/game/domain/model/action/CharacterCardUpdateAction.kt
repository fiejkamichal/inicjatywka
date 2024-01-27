package org.mechanika.inicjatywka.game.domain.model.action

data class CharacterCardUpdateAction(
    val cardId: Long,
    val prevCardId: Long,
    override val type: ActionStackEntry.ActionTypes = ActionStackEntry.ActionTypes.CharacterCardUpdate,
    override var id: Long? = null
) : Action