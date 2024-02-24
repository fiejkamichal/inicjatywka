package org.mechanika.inicjatywka.game.data.data_source.action

import org.mechanika.inicjatywka.database.ActionStackEntryEntity
import org.mechanika.inicjatywka.database.ActionStackPositionEntity
import org.mechanika.inicjatywka.database.CardAddActionEntity
import org.mechanika.inicjatywka.database.CardDeleteActionEntity
import org.mechanika.inicjatywka.database.CardUpdateActionEntity
import org.mechanika.inicjatywka.database.NextTurnActionEntity
import org.mechanika.inicjatywka.database.PhaseChangeActionEntity
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Engine

fun ActionStackEntryEntity.toActionStackEntry(): ActionStackEntry {
    return ActionStackEntry(
        position = position,
        actionType = ActionStackEntry.ActionTypes.entries.find { it.value == this.actionType } ?: error("Invalid actionType ${this.actionType}"),
        actionId = actionId
    )
}


fun ActionStackPositionEntity.toLong(): Long {
    return this.actionStackEntryPosition
}


fun PhaseChangeActionEntity.toPhaseChangeAction(): PhaseChangeAction {
    return PhaseChangeAction(
        id = this.id,
        from = Engine.Phases.valueOf(this.fromPhase),
        to = Engine.Phases.valueOf(this.toPhase),
        cardId = this.cardId,
        round = this.round
    )
}

fun CardAddActionEntity.toCardAddAction(): CardAddAction {
    return CardAddAction(
        id = this.id,
        cardId = this.cardId
    )
}

fun CardDeleteActionEntity.toCardDeleteAction(): CardDeleteAction {
    return CardDeleteAction(
        id = this.id,
        cardId = this.cardId
    )
}

fun CardUpdateActionEntity.toCardUpdateAction(): CardUpdateAction {
    return CardUpdateAction(
        id = this.id,
        cardId = this.cardId,
        prevCardId = this.prevCardId
    )
}


fun NextTurnActionEntity.toNextTurnAction(): NextTurnAction {
    return NextTurnAction(
        id = this.id,
        fromCardId = this.fromCardId,
        toCardId = this.toCardId
    )
}
