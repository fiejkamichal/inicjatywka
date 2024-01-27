package org.mechanika.inicjatywka.game.domain.use_case.action

data class Actions(
    val actionUseCaseEmpty: ActionUseCaseEmpty,
    val actionUseCasePhaseChange: ActionUseCasePhaseChange,
    val actionUseCaseCharacterCardAdd: ActionUseCaseCharacterCardAdd,
    val actionUseCaseCharacterCardDelete: ActionUseCaseCharacterCardDelete,
    val actionUseCaseCharacterCardUpdate: ActionUseCaseCharacterCardUpdate
)