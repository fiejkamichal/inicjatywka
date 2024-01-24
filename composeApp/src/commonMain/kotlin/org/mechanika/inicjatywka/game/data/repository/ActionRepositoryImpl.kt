package org.mechanika.inicjatywka.game.data.repository

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.data.data_source.action.ActionDao
import org.mechanika.inicjatywka.game.domain.model.action.ActionStackEntry
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardAddAction
import org.mechanika.inicjatywka.game.domain.model.action.CharacterCardDeleteAction
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

    override fun insertCharacterCardAddAction(characterCardAdd: CharacterCardAddAction): Long {
        return dao.insertCharacterCardAddAction(characterCardAdd.cardId)
    }

    override fun deleteCharacterCardAddAction(actionId: Long): Long {
        val card = dao.getCharacterCardAddAction(actionId)
        if (card != null) {
            dao.deleteCharacterCardAddAction(actionId)
            return card.cardId
        }
        return 0
    }

    override fun getCharacterCardAddAction(actionId: Long): CharacterCardAddAction? {
        return dao.getCharacterCardAddAction(actionId)
    }

    override fun getCharacterCardAddActions(): Flow<List<CharacterCardAddAction>> {
        return dao.getCharacterCardAddActions()
    }


    override fun insertCharacterCardDeleteAction(characterCardDelete: CharacterCardDeleteAction): Long =
        dao.insertCharacterCardDeleteAction(characterCardDelete.cardId)

    override fun deleteCharacterCardDeleteAction(actionId: Long) =
        dao.deleteCharacterCardDeleteAction(actionId)

    override fun getCharacterCardDeleteAction(actionId: Long): CharacterCardDeleteAction? =
        dao.getCharacterCardDeleteAction(actionId)

    override fun getCharacterCardDeleteActions(): Flow<List<CharacterCardDeleteAction>> =
        dao.getCharacterCardDeleteActions()

}