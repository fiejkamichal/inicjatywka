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
        CardUpdate(3),
        NextTurn(4),
        NextRound(5),
        ActionList(6);

        companion object {
            private val map = entries.associateBy { it.value }
            infix fun from(value: Long) = map[value]
        }
    }
}
