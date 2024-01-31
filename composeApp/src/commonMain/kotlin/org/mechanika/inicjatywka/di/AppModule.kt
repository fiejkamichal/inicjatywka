package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.repository.ActionRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.CardRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.PhaseRepositoryImpl
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.PhaseRepository
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardAdd
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardDelete
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardUpdate
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseEmpty
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCasePhaseChange
import org.mechanika.inicjatywka.game.domain.use_case.action.Actions
import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo
import org.mechanika.inicjatywka.game.domain.use_case.card.AddCard
import org.mechanika.inicjatywka.game.domain.use_case.card.DeleteCard
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCard
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCards
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard
import org.mechanika.inicjatywka.game.domain.use_case.debug.Debug
import org.mechanika.inicjatywka.game.domain.use_case.phase.GetPhase
import org.mechanika.inicjatywka.game.domain.use_case.phase.StartInitiative
import org.mechanika.inicjatywka.game.domain.use_case.phase.StopInitiative
import org.mechanika.inicjatywka.game.presentation.components.card.CardViewModel
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel


class AppModule(
    appModulePlatform: AppModulePlatform
) {
    private val phaseRepository: PhaseRepository = PhaseRepositoryImpl(
        dao = appModulePlatform.phaseDao
    )

    private val actionRepository: ActionRepository = ActionRepositoryImpl(
        dao = appModulePlatform.actionDao
    )

    private val cardRepository: CardRepository = CardRepositoryImpl(
        dao = appModulePlatform.cardDao
    )

    private val actions: Actions = Actions(
        actionUseCaseEmpty = ActionUseCaseEmpty(),
        actionUseCasePhaseChange = ActionUseCasePhaseChange(phaseRepository, actionRepository),
        actionUseCaseCardAdd = ActionUseCaseCardAdd(
            cardRepository,
            actionRepository
        ),
        actionUseCaseCardDelete = ActionUseCaseCardDelete(
            cardRepository,
            actionRepository
        ),
        actionUseCaseCardUpdate = ActionUseCaseCardUpdate(
            cardRepository,
            actionRepository
        )
    )

    private val stack: Stack = Stack(actionRepository, actions)

    private val debug: Debug = Debug(
        actionRepository = actionRepository,
        phaseRepository = phaseRepository,
        cardRepository = cardRepository
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
        addCard = AddCard(cardRepository, stack),
        deleteCard = DeleteCard(cardRepository, stack),
        getCard = GetCard(cardRepository),
        getCards = GetCards(cardRepository),
        updateCard = UpdateCard(cardRepository, stack)
    )

    private val undoRedoViewModel = UndoRedoViewModel(
        undo = inicjatywkaUseCases.undoAction,
        redo = inicjatywkaUseCases.redoAction,
        stack = stack
    )

    private val debugViewModel = DebugViewModel(inicjatywkaUseCases)

    fun getUndoRedoViewModel(): UndoRedoViewModel {
        return undoRedoViewModel
    }

    fun getDebugViewModel(): DebugViewModel {
        return debugViewModel
    }

    fun getCardViewModel(): CardViewModel {
        return CardViewModel(
            addCard = inicjatywkaUseCases.addCard,
            deleteCard = inicjatywkaUseCases.deleteCard,
            getCards = inicjatywkaUseCases.getCards,
            updateCard = inicjatywkaUseCases.updateCard
        )
    }
}