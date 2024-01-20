package org.mechanika.inicjatywka.game.domain.use_case.action

class Undo(
    private val stack: Stack,
    private val actions: Actions
) {
    operator fun invoke(){
        val action = stack.getActionOnCurrentPosition()
        getActionUseCase(actions, action).undo(action)
        stack.movePositionDown()
    }
}