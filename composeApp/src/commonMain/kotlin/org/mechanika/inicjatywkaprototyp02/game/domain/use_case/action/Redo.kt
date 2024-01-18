package org.mechanika.inicjatywkaprototyp02.game.domain.use_case.action

class Redo(
    private val stack: Stack,
    private val actions: Actions
) {
    operator fun invoke(){
        stack.movePositionUp()
        val action = stack.getActionOnCurrentPosition()
        getActionUseCase(actions, action).redo(action)
    }
}