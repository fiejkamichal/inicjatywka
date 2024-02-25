package org.mechanika.inicjatywka.game.domain.use_case.debug

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywka.game.domain.model.action.ActionListItem
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository

class Debug(
    val actionRepository: ActionRepository,
    val engineRepository: EngineRepository,
    val cardRepository: CardRepository
) {
    fun getCards(): Flow<List<Card>> {
        return cardRepository.getCardsAsFlow()
    }

    fun getDeletedCards(): Flow<List<Card>> {
        return cardRepository.getDeletedCards()
    }

    fun getChangePhases(): Flow<List<PhaseChangeAction>> {
        return actionRepository.getPhaseChangeActions()
    }

    fun getPhase(): Flow<Engine.Phases?> {
        return engineRepository.getPhase()
    }

    fun getPhases(): Flow<List<Engine.Phases>> {
        return engineRepository.getPhases()
    }

    fun getCurrentCardId(): Flow<Long?> {
        return engineRepository.getCurrentCardIdAsFlow()
    }

    fun getCurrentCardIds(): Flow<List<Long?>> {
        return engineRepository.getCurrentCardIds()
    }

    fun getCurrentStackPosition(): Flow<Long?> {
        return actionRepository.getActionStackPositionFlow()
    }

    fun getActionListActionIds(): Flow<List<Long>> {
        return actionRepository.getActionListActionIds()
    }

    fun getActionListItems(): Flow<List<ActionListItem>> {
        return actionRepository.getActionListItems()
    }

    fun getAllActions(): Flow<List<List<String>>> {
        return actionRepository.getAllActions()
    }
}