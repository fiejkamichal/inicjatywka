package org.mechanika.inicjatywka.game.domain.model.action

interface Action {
    var id: Long?
    val type: ActionStackEntry.ActionTypes
}