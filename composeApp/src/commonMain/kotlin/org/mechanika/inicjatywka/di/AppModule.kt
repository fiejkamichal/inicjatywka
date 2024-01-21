package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.repository.ActionRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.CharacterRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.PhaseRepositoryImpl
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.domain.use_case.action.Actions
import org.mechanika.inicjatywka.game.domain.use_case.action.CharacterCardAddActionUseCase
import org.mechanika.inicjatywka.game.domain.use_case.action.CharacterCardDeleteActionUseCase
import org.mechanika.inicjatywka.game.domain.use_case.action.EmptyActionUseCase
import org.mechanika.inicjatywka.game.domain.use_case.action.PhaseChangeActionUseCase
import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo
import org.mechanika.inicjatywka.game.domain.use_case.debug.Debug
import org.mechanika.inicjatywka.game.domain.use_case.phase.GetPhase
import org.mechanika.inicjatywka.game.domain.use_case.phase.StartInitiative
import org.mechanika.inicjatywka.game.domain.use_case.phase.StopInitiative


class AppModule (
    appModulePlatform: AppModulePlatform
) {
    private val phaseRepository:PhaseRepository = PhaseRepositoryImpl(
        dao = appModulePlatform.phaseDao
    )

    private val actionRepository: ActionRepository = ActionRepositoryImpl(
        dao = appModulePlatform.actionDao
    )

    private val characterRepository: CharacterRepository = CharacterRepositoryImpl(
        dao = appModulePlatform.characterDao
    )

    private val stack: Stack = Stack(actionRepository)

    private val actions: Actions = Actions (
        emptyActionUseCase = EmptyActionUseCase(),
        phaseChangeActionUseCase = PhaseChangeActionUseCase(phaseRepository),
        characterCardAddActionUseCase = CharacterCardAddActionUseCase(characterRepository),
        characterCardDeleteActionUseCase = CharacterCardDeleteActionUseCase(characterRepository)
    )

    private val debug: Debug = Debug(
        actionRepository = actionRepository,
        phaseRepository = phaseRepository,
        characterRepository = characterRepository
    )

    val inicjatywkaUseCases = InicjatywkaUseCases(
        startInitiative = StartInitiative(phaseRepository, stack),
        stopInitiative = StopInitiative(phaseRepository, stack),
        getPhase = GetPhase(phaseRepository),
        actions = actions,
        undoAction = Undo(stack, actions),
        redoAction = Redo(stack, actions),
        stack = stack,
        debug = debug
    )
}