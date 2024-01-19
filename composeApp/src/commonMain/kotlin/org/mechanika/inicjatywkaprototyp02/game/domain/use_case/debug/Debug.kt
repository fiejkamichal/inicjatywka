package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.debug

import kotlinx.coroutines.flow.Flow
import org.mechanika.inicjatywkaprototyp02.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.ActionRepository
import org.mechanika.inicjatywkaprototyp02.game.domain.repository.PhaseRepository

class Debug(
    val actionRepository: ActionRepository,
    val phaseRepository: PhaseRepository
) {
    fun getChangePhases(): Flow<List<PhaseChangeAction>> {
        return actionRepository.getPhaseChangeActions()
    }
    fun getPhase(): Flow<Phase?> {
        return phaseRepository.getPhase()
    }
    fun getPhases(): Flow<List<Phase>> {
        return phaseRepository.getPhases()
    }

    fun getCurrentStackPosition():Long? {
        return actionRepository.getActionStackPosition()
    }
}