package org.mechanika.inicjatywka.game.data.data_source.action

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.database.InicjatywkaDatabase
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction


class ActionDaoImpl(
    db: InicjatywkaDatabase
) : ActionDao {
    val queries = db.actionQueries
    override fun getActionStackEntry(position: Long): ActionStackEntry? {
        return queries.getActionStackEntry(position).executeAsOneOrNull()?.toActionStackEntry()
    }

    override fun setActionStackEntry(
        position: Long,
        actionType: ActionStackEntry.ActionTypes,
        actionId: Long
    ) {
        queries.setActionStackEntry(position, actionType.value, actionId)
    }

    override fun deleteActionStackEntry(position: Long) {
        queries.deleteActionStackEntry(position)
    }

    override fun getNumOfActionStackEntries(): Long {
        return queries.getNumOfActionStackEntries().executeAsOneOrNull() ?: 0
    }

    override fun getNumOfActionStackEntriesFlow(): Flow<Long> {
        return queries.getNumOfActionStackEntries()
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }

    override fun getActionStackEntries(): Flow<List<ActionStackEntry>> {

        return queries.getActionStackEntries()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toActionStackEntry()
                }
            }
    }

    override fun getActionStackPosition(): Long? {
        return queries.getActionStackPositionEntity().executeAsOneOrNull()?.toLong()
    }

    override fun getActionStackPositionFlow(): Flow<Long> {
        return queries.getActionStackPositionEntity()
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map {
                it?.toLong() ?: 0
            }
    }

    override fun setActionStackPosition(position: Long) {
        return queries.setActionStackPositionEntity(position)
    }

    override fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long {
        return queries.transactionWithResult {
            queries.insertPhaseChangeAction(
                fromPhase = phaseChange.from.toString(),
                toPhase = phaseChange.to.toString(),
                cardId = phaseChange.cardId,
                round = phaseChange.round
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deletePhaseChangeAction(actionId: Long) {
        queries.deletePhaseChangeAction(actionId)
    }

    override fun getPhaseChangeAction(actionId: Long): PhaseChangeAction? {
        return queries.getPhaseChangeAction(actionId).executeAsOneOrNull()?.toPhaseChangeAction()
    }

    override fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>> {
        return queries.getPhaseChangeActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toPhaseChangeAction()
                }
            }
    }

    override fun insertCardAddAction(cardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertCardAddAction(cardId = cardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteCardAddAction(actionId: Long) {
        queries.deleteCardAddAction(actionId)
    }

    override fun getCardAddAction(actionId: Long): CardAddAction? {
        return queries.getCardAddAction(actionId).executeAsOneOrNull()
            ?.toCardAddAction()
    }

    override fun getCardAddActions(): Flow<List<CardAddAction>> {
        return queries.getCardAddActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCardAddAction()
                }
            }
    }


    override fun insertCardDeleteAction(cardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertCardDeleteAction(cardId = cardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteCardDeleteAction(actionId: Long) {
        queries.deleteCardDeleteAction(actionId)
    }

    override fun getCardDeleteAction(actionId: Long): CardDeleteAction? {
        return queries.getCardDeleteAction(actionId).executeAsOneOrNull()
            ?.toCardDeleteAction()
    }

    override fun getCardDeleteActions(): Flow<List<CardDeleteAction>> {
        return queries.getCardDeleteActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCardDeleteAction()
                }
            }
    }


    override fun insertCardUpdateAction(cardId: Long, prevCardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertCardUpdateAction(cardId = cardId, prevCardId = prevCardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteCardUpdateAction(actionId: Long) {
        queries.deleteCardUpdateAction(actionId)
    }

    override fun getCardUpdateAction(actionId: Long): CardUpdateAction? {
        return queries.getCardUpdateAction(actionId).executeAsOneOrNull()
            ?.toCardUpdateAction()
    }

    override fun getCardUpdateActions(): Flow<List<CardUpdateAction>> {
        return queries.getCardUpdateActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCardUpdateAction()
                }
            }
    }


    override fun insertNextTurnAction(fromCardId: Long, toCardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertNextTurnAction(fromCardId = fromCardId, toCardId = toCardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteNextTurnAction(actionId: Long) {
        queries.deleteNextTurnAction(actionId)
    }

    override fun getNextTurnAction(actionId: Long): NextTurnAction? {
        return queries.getNextTurnAction(actionId).executeAsOneOrNull()
            ?.toNextTurnAction()
    }

    override fun getNextTurnActions(): Flow<List<NextTurnAction>> {
        return queries.getNextTurnActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toNextTurnAction()
                }
            }
    }
}
