package org.mechanika.inicjatywkaprototyp02.game.data.data_source.action

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywkaprototyp02.actiondatabase.ActionDatabase
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction


class ActionDaoImpl(
    db: ActionDatabase
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
        queries.setActionStackEntry(position, actionType.toString(), actionId)
    }

    override fun deleteActionStackEntry(position: Long) {
        queries.deleteActionStackEntry(position)
    }

    override fun getNumOfActionStackEntries(): Long {
        return queries.getNumOfActionStackEntries().executeAsOne()
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

    override fun setActionStackPosition(position: Long) {
        return queries.setActionStackPositionEntity(position)
    }

    override fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long {
        return queries.transactionWithResult {
            queries.insertPhaseChangeAction(phaseChange.from.toString(), phaseChange.to.toString(), phaseChange.type.toString())
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

}
