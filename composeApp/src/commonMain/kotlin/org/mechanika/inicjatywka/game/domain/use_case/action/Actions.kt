package org.mechanika.inicjatywka.game.domain.use_case.action

data class Actions(
    val actionUseCaseEmpty: ActionUseCaseEmpty,
    val actionUseCasePhaseChange: ActionUseCasePhaseChange,
    val actionUseCaseCardAdd: ActionUseCaseCardAdd,
    val actionUseCaseCardDelete: ActionUseCaseCardDelete,
    val actionUseCaseCardUpdate: ActionUseCaseCardUpdate,
    val actionUseCaseNextTurn: ActionUseCaseNextTurn
)