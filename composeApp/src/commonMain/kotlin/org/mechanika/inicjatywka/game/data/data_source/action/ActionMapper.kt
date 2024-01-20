package org.mechanika.inicjatywka.game.data.data_source.action

import org.mechanika.inicjatywka.actiondatabase.ActionStackEntryEntity
import org.mechanika.inicjatywka.actiondatabase.ActionStackPositionEntity
import org.mechanika.inicjatywka.actiondatabase.PhaseChangeActionEntity
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.phase.Phase

fun ActionStackEntryEntity.toActionStackEntry(): ActionStackEntry {
    return ActionStackEntry(
        position = position,
        actionType = ActionStackEntry.ActionTypes.valueOf(this.actionType),
        actionId = actionId
    )
}


fun ActionStackPositionEntity.toLong(): Long? {
    return this.actionStackEntryPosition
}


fun PhaseChangeActionEntity.toPhaseChangeAction(): PhaseChangeAction {
    return PhaseChangeAction(
        id = this.id,
        from = Phase.Phases.valueOf(this.fromPhase),
        to = Phase.Phases.valueOf(this.toPhase),
        type = ActionStackEntry.ActionTypes.valueOf(this.type)
    )
}