package org.mechanika.inicjatywka.game.domain.use_case.action

import org.mechanika.inicjatywka.game.domain.model.action.Action
import org.mechanika.inicjatywka.game.domain.model.action.ActionListAction
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository

class ActionUseCaseActionList(
    private val actionRepository: ActionRepository,
    private val actions: Actions
) : ActionUseCase() {
    override fun undo(action: Action) {
        val a = action as? ActionListAction
        a?.actions?.forEach {
            getActionUseCase(actions, it.type).undo(it)
        }
    }

    override fun redo(action: Action) {
        val a = action as? ActionListAction
        a?.actions?.forEach {
            getActionUseCase(actions, it.type).redo(it)
        }
    }

    override fun deleteFromSubRepository(action: Action) {
        actionRepository.deleteActionListAction(action.id!!)
    }

    override fun insertToSubRepository(action: Action): Long =
        actionRepository.insertActionListAction(action as ActionListAction)

    override fun get(id: Long): Action =
        actionRepository.getActionListAction(id)
}