package org.mechanika.inicjatywka.game.domain.use_case.action

data class Actions(
    val emptyActionUseCase: EmptyActionUseCase,
    val phaseChangeActionUseCase: PhaseChangeActionUseCase,
    val characterCardAddActionUseCase: CharacterCardAddActionUseCase,
    val characterCardDeleteActionUseCase: CharacterCardDeleteActionUseCase
)