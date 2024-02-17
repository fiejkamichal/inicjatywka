package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.engine.changePhase

class ActionUseCasePhaseChange(
    private val engineRepository: EngineRepository,
    private val actionRepository: ActionRepository
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null) {
            if (a.from == Phase.Phases.Initial)
                changePhase(
                    to = Phase.Phases.Initial,
                    cardId = null,
                    repository = engineRepository
                )
            else
                changePhase(
                    to = Phase.Phases.Initiative,
                    cardId = a.cardId,
                    repository = engineRepository
                )
        }
    }

    override fun redo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null)
            if (a.to == Phase.Phases.Initial)
                changePhase(
                    to = Phase.Phases.Initial,
                    cardId = null,
                    repository = engineRepository
                )
            else
                changePhase(
                    to = Phase.Phases.Initiative,
                    cardId = a.cardId,
                    repository = engineRepository
                )
    }

    override fun deleteFromSubRepository(action: Action) =
        actionRepository.deletePhaseChangeAction(action.id!!)

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertPhaseChangeAction(action as PhaseChangeAction)

    override fun get(id: Long): Action? =
        actionRepository.getPhaseChangeAction(id)
}