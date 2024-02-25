package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.model.action.ActionListItem
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CardDeleteAction
import org.mechanika.inicjatywka.game.domain.model.action.CardUpdateAction
import org.mechanika.inicjatywka.game.domain.model.action.NextRoundAction
import org.mechanika.inicjatywka.game.domain.model.action.NextTurnAction
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository

class ActionRepositoryImpl(
    val dao: ActionDao
) : ActionRepository {
    override fun getActionStackEntry(position: Long): ActionStackEntry? {
        return dao.getActionStackEntry(position)
    }

    override fun setActionStackEntry(
        position: Long,
        actionType: ActionStackEntry.ActionTypes,
        actionId: Long
    ) {
        dao.setActionStackEntry(
            position,
            actionType,
            actionId
        )
    }

    override fun deleteActionStackEntry(position: Long) {
        dao.deleteActionStackEntry(position)
    }


    override fun getNumOfActionStackEntries(): Long {
        return dao.getNumOfActionStackEntries()
    }

    override fun getNumOfActionStackEntriesFlow(): Flow<Long> {
        return dao.getNumOfActionStackEntriesFlow()
    }

    override fun getActionStackEntries(): Flow<List<ActionStackEntry>> {
        return dao.getActionStackEntries()
    }

    override fun getActionStackPosition(): Long? {
        return dao.getActionStackPosition()
    }

    override fun getActionStackPositionFlow(): Flow<Long> {
        return dao.getActionStackPositionFlow()
    }

    override fun setActionStackPosition(position: Long): Long {
        dao.setActionStackPosition(position)
        return position
    }

    override fun insertPhaseChangeAction(phaseChange: PhaseChangeAction): Long {
        return dao.insertPhaseChangeAction(phaseChange)
    }

    override fun deletePhaseChangeAction(actionId: Long) {
        dao.deletePhaseChangeAction(actionId)
    }

    override fun getPhaseChangeAction(actionId: Long): PhaseChangeAction? {
        return dao.getPhaseChangeAction(actionId)
    }

    override fun getPhaseChangeActions(): Flow<List<PhaseChangeAction>> {
        return dao.getPhaseChangeActions()
    }

    override fun insertCardAddAction(cardAdd: CardAddAction): Long {
        return dao.insertCardAddAction(cardAdd.cardId)
    }

    override fun deleteCardAddAction(actionId: Long): Long {
        val card = dao.getCardAddAction(actionId)
        if (card != null) {
            dao.deleteCardAddAction(actionId)
            return card.cardId
        }
        return 0
    }

    override fun getCardAddAction(actionId: Long): CardAddAction? {
        return dao.getCardAddAction(actionId)
    }

    override fun getCardAddActions(): Flow<List<CardAddAction>> {
        return dao.getCardAddActions()
    }


    override fun insertCardDeleteAction(cardDelete: CardDeleteAction): Long =
        dao.insertCardDeleteAction(cardDelete.cardId)

    override fun deleteCardDeleteAction(actionId: Long) =
        dao.deleteCardDeleteAction(actionId)

    override fun getCardDeleteAction(actionId: Long): CardDeleteAction? =
        dao.getCardDeleteAction(actionId)

    override fun getCardDeleteActions(): Flow<List<CardDeleteAction>> =
        dao.getCardDeleteActions()

    override fun insertCardUpdateAction(cardUpdate: CardUpdateAction): Long =
        dao.insertCardUpdateAction(
            cardUpdate.cardId,
            cardUpdate.prevCardId
        )

    override fun deleteCardUpdateAction(actionId: Long) =
        dao.deleteCardUpdateAction(actionId)

    override fun getCardUpdateAction(actionId: Long): CardUpdateAction? =
        dao.getCardUpdateAction(actionId)

    override fun getCardUpdateActions(): Flow<List<CardUpdateAction>> =
        dao.getCardUpdateActions()


    override fun insertNextTurnAction(nextTurn: NextTurnAction): Long =
        dao.insertNextTurnAction(nextTurn)

    override fun deleteNextTurnAction(actionId: Long) =
        dao.deleteNextTurnAction(actionId)

    override fun getNextTurnAction(actionId: Long): NextTurnAction? =
        dao.getNextTurnAction(actionId)

    override fun getNextTurnActions(): Flow<List<NextTurnAction>> =
        dao.getNextTurnActions()

    override fun insertNextRoundAction(nextRound: NextRoundAction): Long =
        dao.insertNextRoundAction(
            nextRound
        )

    override fun deleteNextRoundAction(actionId: Long) =
        dao.deleteNextRoundAction(actionId)

    override fun getNextRoundAction(actionId: Long): NextRoundAction? =
        dao.getNextRoundAction(actionId)

    override fun getNextRoundActions(): Flow<List<NextRoundAction>> =
        dao.getNextRoundActions()


    override fun insertActionListAction(actionListAction: ActionListAction): Long {
        return dao.insertActionListAction(actionListAction)
    }

    override fun deleteActionListAction(actionListId: Long) {
        dao.deleteActionListAction(actionListId)
    }

    override fun getActionListAction(actionListId: Long): ActionListAction {
        return dao.getActionListAction(actionListId)
    }

    override fun getActionListActionIds(): Flow<List<Long>> {
        return dao.getActionListActionIds()
    }

    override fun getActionListItems(): Flow<List<ActionListItem>> {
        return dao.getAllActionListItems()
    }

    override fun getAllActions(): Flow<List<List<String>>> {
        return dao.getAllActions()
    }
}