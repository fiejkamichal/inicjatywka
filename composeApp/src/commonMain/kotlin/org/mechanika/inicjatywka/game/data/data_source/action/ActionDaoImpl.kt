package org.mechanika.inicjatywka.game.data.data_source.action

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.database.InicjatywkaDatabase
import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.ActionListItem
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextRoundAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction


class ActionDaoImpl(
    val db: InicjatywkaDatabase
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


    override fun insertNextTurnAction(nextTurn: NextTurnAction): Long {
        return queries.transactionWithResult {
            queries.insertNextTurnAction(
                fromCardId = nextTurn.fromCardId,
                toCardId = nextTurn.toCardId,
                fromReverse = if (nextTurn.fromReverse) 1L else 0L,
                toReverse = if (nextTurn.toReverse) 1L else 0L
            )
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


    override fun insertNextRoundAction(nextRound: NextRoundAction): Long {
        return queries.transactionWithResult {
            queries.insertNextRoundAction(
                fromCardId = nextRound.fromCardId,
                toCardId = nextRound.toCardId,
                fromRound = nextRound.fromRound,
                toRound = nextRound.toRound,
                fromReverse = if (nextRound.fromReverse) 1L else 0L,
                toReverse = if (nextRound.toReverse) 1L else 0L
            )
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteNextRoundAction(actionId: Long) {
        queries.deleteNextRoundAction(actionId)
    }

    override fun getNextRoundAction(actionId: Long): NextRoundAction? {
        return queries.getNextRoundAction(actionId).executeAsOneOrNull()
            ?.toNextRoundAction()
    }

    override fun getNextRoundActions(): Flow<List<NextRoundAction>> {
        return queries.getNextRoundActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toNextRoundAction()
                }
            }
    }


    override fun insertActionListAction(actionListAction: ActionListAction): Long {
        return queries.transactionWithResult {
            queries.insertActionListAction()
            val actionListId = queries.lastInsertRowId().executeAsOne()
            actionListAction.actions.forEachIndexed { index, action ->
                queries.insertActionListItem(
                    actionListId = actionListId,
                    actionId = action.id ?: 0,
                    actionType = action.type.value,
                    listPosition = index.toLong()
                )
            }
            actionListId
        }
    }

    override fun deleteActionListAction(actionListId: Long) {
        queries.transaction {
            queries.deleteActionListAction(actionListId)
            queries.deleteActionListItems(actionListId)
        }
    }

    override fun getActionListAction(actionListId: Long): ActionListAction {
        return queries.transactionWithResult {
            val actions = queries.getActionListItems(actionListId)
                .executeAsList().mapNotNull {
                    if (it.actionId != 0L) {
                        val actionType = (ActionStackEntry.ActionTypes from it.actionType)
                            ?: error("Invalid actionType ${it.actionType}")
                        when (actionType) {
                            ActionStackEntry.ActionTypes.PhaseChange -> getPhaseChangeAction(it.actionId)
                            ActionStackEntry.ActionTypes.CardAdd -> getCardAddAction(it.actionId)
                            ActionStackEntry.ActionTypes.CardDelete -> getCardDeleteAction(it.actionId)
                            ActionStackEntry.ActionTypes.CardUpdate -> getCardUpdateAction(it.actionId)
                            ActionStackEntry.ActionTypes.NextTurn -> getNextTurnAction(it.actionId)
                            ActionStackEntry.ActionTypes.NextRound -> getNextRoundAction(it.actionId)
                            ActionStackEntry.ActionTypes.ActionList -> TODO()
                        } as Action
                    } else null
                }

            ActionListAction(
                id = actionListId,
                actions = actions
            )
        }
    }

    override fun getActionListActionIds(): Flow<List<Long>> {
        return queries.getActionListActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override fun getAllActionListItems(): Flow<List<ActionListItem>> {
        return queries.getAllActionListItems()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map { it.toActionListItem() }
            }
    }

    override fun getAllActions(): Flow<List<List<String>>> {
        return combine(
            getCardAddActions(),
            getCardDeleteActions(),
            getCardUpdateActions(),
            getNextTurnActions(),
            getNextRoundActions(),
            getPhaseChangeActions()
        ) { a ->
            a.toList().map { list ->
                list.map {
                    it.toString()
                }
            }
        }
    }

}
