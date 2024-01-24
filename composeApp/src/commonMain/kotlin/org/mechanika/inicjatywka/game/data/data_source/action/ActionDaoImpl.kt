package org.mechanika.inicjatywka.game.data.data_source.action

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mechanika.inicjatywka.actiondatabase.ActionDatabase
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction


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
                phaseChange.from.toString(),
                phaseChange.to.toString(),
                phaseChange.type.toString()
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

    override fun insertCharacterCardAddAction(cardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertCharacterCardAddAction(cardId = cardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteCharacterCardAddAction(actionId: Long) {
        queries.deleteCharacterCardAddAction(actionId)
    }

    override fun getCharacterCardAddAction(actionId: Long): CharacterCardAddAction? {
        return queries.getCharacterCardAddAction(actionId).executeAsOneOrNull()
            ?.toCharacterCardAddAction()
    }

    override fun getCharacterCardAddActions(): Flow<List<CharacterCardAddAction>> {
        return queries.getCharacterCardAddActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCharacterCardAddAction()
                }
            }
    }


    override fun insertCharacterCardDeleteAction(cardId: Long): Long {
        return queries.transactionWithResult {
            queries.insertCharacterCardDeleteAction(cardId = cardId)
            queries.lastInsertRowId().executeAsOne()
        }
    }

    override fun deleteCharacterCardDeleteAction(actionId: Long) {
        queries.deleteCharacterCardDeleteAction(actionId)
    }

    override fun getCharacterCardDeleteAction(actionId: Long): CharacterCardDeleteAction? {
        return queries.getCharacterCardDeleteAction(actionId).executeAsOneOrNull()
            ?.toCharacterCardDeleteAction()
    }

    override fun getCharacterCardDeleteActions(): Flow<List<CharacterCardDeleteAction>> {
        return queries.getCharacterCardDeleteActions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    it.toCharacterCardDeleteAction()
                }
            }
    }

}
