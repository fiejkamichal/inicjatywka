package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.repository.ActionRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.CharacterRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.PhaseRepositoryImpl
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CharacterRepository
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.domain.use_case.action.Actions
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCharacterCardAdd
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCharacterCardDelete
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseEmpty
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCasePhaseChange
import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo
import org.mechanika.inicjatywka.game.domain.use_case.character.AddCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.DeleteCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCard
import org.mechanika.inicjatywka.game.domain.use_case.character.GetCharacterCards
import org.mechanika.inicjatywka.game.domain.use_case.debug.Debug
import org.mechanika.inicjatywka.game.domain.use_case.phase.GetPhase
import org.mechanika.inicjatywka.game.domain.use_case.phase.StartInitiative
import org.mechanika.inicjatywka.game.domain.use_case.phase.StopInitiative


class AppModule(
    appModulePlatform: AppModulePlatform
) {
    private val phaseRepository: PhaseRepository = PhaseRepositoryImpl(
        dao = appModulePlatform.phaseDao
    )

    private val actionRepository: ActionRepository = ActionRepositoryImpl(
        dao = appModulePlatform.actionDao
    )

    private val characterRepository: CharacterRepository = CharacterRepositoryImpl(
        dao = appModulePlatform.characterDao
    )

    private val actions: Actions = Actions(
        actionUseCaseEmpty = ActionUseCaseEmpty(),
        actionUseCasePhaseChange = ActionUseCasePhaseChange(phaseRepository, actionRepository),
        actionUseCaseCharacterCardAdd = ActionUseCaseCharacterCardAdd(characterRepository, actionRepository),
        actionUseCaseCharacterCardDelete = ActionUseCaseCharacterCardDelete(characterRepository, actionRepository)
    )

    private val stack: Stack = Stack(actionRepository, actions)

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
        debug = debug,
        addCharacterCard = AddCharacterCard(characterRepository, stack),
        deleteCharacterCard = DeleteCharacterCard(characterRepository, stack),
        getCharacterCard = GetCharacterCard(characterRepository),
        getCharacterCards = GetCharacterCards(characterRepository)
    )
}