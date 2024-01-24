package org.mechanika.inicjatywka.game.domain.use_case.action

class Redo(
    private val stack: Stack,
    private val actions: Actions
) {
    operator fun invoke() {
        stack.movePositionUp()
        val action = stack.getActionOnCurrentPosition()
        if (action != null) ActionUseCase.getActionUseCase(actions, action.type).redo(action)
    }
}