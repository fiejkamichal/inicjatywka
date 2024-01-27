package org.mechanika.inicjatywka.game.domain.model.action

data class ActionStackEntry(
    val position: Long,
    val actionType: ActionTypes,
    val actionId: Long
) {
    enum class ActionTypes {
        PhaseChange,
        CharacterCardAdd,
        CharacterCardDelete,
        CharacterCardUpdate
    }
}
