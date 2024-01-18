package org.mechanika.inicjatywkaprototyp02.game.domain.model.action

data class ActionStackEntry(
    val position: Long,
    val actionType: ActionTypes,
    val actionId: Long
) {
    enum class ActionTypes {
        PhaseChange
    }
}
