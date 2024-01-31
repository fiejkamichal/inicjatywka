package org.mechanika.inicjatywka.game.domain.model.action

data class ActionStackEntry(
    val position: Long,
    val actionType: ActionTypes,
    val actionId: Long
) {
    enum class ActionTypes(val value: Long) {
        PhaseChange(0),
        CardAdd(1),
        CardDelete(2),
        CardUpdate(3);
    }
}
