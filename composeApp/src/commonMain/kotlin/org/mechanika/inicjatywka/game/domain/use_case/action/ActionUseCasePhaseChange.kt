package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.PhaseChangeAction
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
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
            if (a.from == Engine.Phases.Initial)
                changePhase(
                    to = Engine.Phases.Initial,
                    cardId = null,
                    repository = engineRepository,
                    round = 0
                )
            else
                changePhase(
                    to = Engine.Phases.Initiative,
                    cardId = a.cardId,
                    repository = engineRepository,
                    round = a.round
                )
        }
    }

    override fun redo(action: Action) {
        val a = action as? PhaseChangeAction
        if (a != null)
            if (a.to == Engine.Phases.Initial)
                changePhase(
                    to = Engine.Phases.Initial,
                    cardId = null,
                    repository = engineRepository,
                    round = 0
                )
            else
                changePhase(
                    to = Engine.Phases.Initiative,
                    cardId = a.cardId,
                    repository = engineRepository,
                    round = a.round
                )
    }

    override fun deleteFromSubRepository(action: Action) =
        actionRepository.deletePhaseChangeAction(action.id!!)

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertPhaseChangeAction(action as PhaseChangeAction)

    override fun get(id: Long): Action? =
        actionRepository.getPhaseChangeAction(id)
}