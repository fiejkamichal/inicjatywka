package org.mechanika.inicjatywka.di

import org.mechanika.inicjatywka.game.data.data_source.action.ActionDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.card.CardDaoImpl
import org.mechanika.inicjatywka.game.data.data_source.engine.EngineDaoImpl
import org.mechanika.inicjatywka.game.data.repository.ActionRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.CardRepositoryImpl
import org.mechanika.inicjatywka.game.data.repository.EngineRepositoryImpl
import org.mechanika.inicjatywka.game.domain.repository.ActionRepository
import org.mechanika.inicjatywka.game.domain.repository.CardRepository
import org.mechanika.inicjatywka.game.domain.repository.EngineRepository
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseActionList
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardAdd
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardDelete
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseCardUpdate
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseEmpty
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseNextRound
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCaseNextTurn
import org.mechanika.inicjatywka.game.domain.use_case.action.ActionUseCasePhaseChange
import org.mechanika.inicjatywka.game.domain.use_case.action.Actions
import org.mechanika.inicjatywka.game.domain.use_case.action.Redo
import org.mechanika.inicjatywka.game.domain.use_case.action.Stack
import org.mechanika.inicjatywka.game.domain.use_case.action.Undo
import org.mechanika.inicjatywka.game.domain.use_case.card.AddCard
import org.mechanika.inicjatywka.game.domain.use_case.card.DeleteCard
import org.mechanika.inicjatywka.game.domain.use_case.card.ExportCards
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCard
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCardIdWithHighestInitiative
import org.mechanika.inicjatywka.game.domain.use_case.card.GetCards
import org.mechanika.inicjatywka.game.domain.use_case.card.ImportCards
import org.mechanika.inicjatywka.game.domain.use_case.card.UpdateCard
import org.mechanika.inicjatywka.game.domain.use_case.debug.Debug
import org.mechanika.inicjatywka.game.domain.use_case.engine.GetCurrentCardId
import org.mechanika.inicjatywka.game.domain.use_case.engine.GetPhase
import org.mechanika.inicjatywka.game.domain.use_case.engine.GetReverse
import org.mechanika.inicjatywka.game.domain.use_case.engine.GetRound
import org.mechanika.inicjatywka.game.domain.use_case.engine.NextRound
import org.mechanika.inicjatywka.game.domain.use_case.engine.NextTurn
import org.mechanika.inicjatywka.game.domain.use_case.engine.StartInitiative
import org.mechanika.inicjatywka.game.domain.use_case.engine.StopInitiative
import org.mechanika.inicjatywka.game.domain.use_case.engine.Wait
import org.mechanika.inicjatywka.game.presentation.components.card.CardEditViewModel
import org.mechanika.inicjatywka.game.presentation.components.card.CardListViewModel
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel


class AppModule(
    appModulePlatform: AppModulePlatform
) {
    private val engineRepository: EngineRepository = EngineRepositoryImpl(
        dao = EngineDaoImpl(
            db = appModulePlatform.db
        )
    )

    private val actionRepository: ActionRepository = ActionRepositoryImpl(
        dao = ActionDaoImpl(
            db = appModulePlatform.db
        )
    )

    private val cardRepository: CardRepository = CardRepositoryImpl(
        dao = CardDaoImpl(
            db = appModulePlatform.db
        )
    )

    private val actions: Actions = Actions(
        actionUseCaseEmpty = ActionUseCaseEmpty(),
        actionUseCasePhaseChange = ActionUseCasePhaseChange(
            engineRepository,
            actionRepository
        ),
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
        ),
        actionUseCaseNextTurn = ActionUseCaseNextTurn(
            engineRepository,
            actionRepository
        ),
        actionUseCaseNextRound = ActionUseCaseNextRound(
            engineRepository,
            actionRepository
        ),
        actionUseCaseActionList = null
    )

    private val actionUseCaseActionList = ActionUseCaseActionList(actionRepository, actions)

    private
    val stack: Stack = Stack(actionRepository, actions)

    private
    val debug: Debug = Debug(
        actionRepository = actionRepository,
        engineRepository = engineRepository,
        cardRepository = cardRepository
    )

    private
    val getCardIdWithHighestInitiative = GetCardIdWithHighestInitiative(cardRepository)

    private val updateCard = UpdateCard(cardRepository, stack)

    private
    val nextRound = NextRound(
        engineRepository,
        cardRepository,
        updateCard,
        getCardIdWithHighestInitiative,
        stack
    )
    val inicjatywkaUseCases = InicjatywkaUseCases(
        startInitiative = StartInitiative(engineRepository, getCardIdWithHighestInitiative, stack),
        stopInitiative = StopInitiative(engineRepository, cardRepository, updateCard, stack),
        getPhase = GetPhase(engineRepository),
        actions = actions,
        undoAction = Undo(stack, actions),
        redoAction = Redo(stack, actions),
        stack = stack,
        debug = debug,
        addCard = AddCard(cardRepository, stack),
        deleteCard = DeleteCard(cardRepository, stack),
        getCard = GetCard(cardRepository),
        getCards = GetCards(cardRepository),
        updateCard = updateCard,
        getCurrentCardId = GetCurrentCardId(engineRepository),
        nextTurn = NextTurn(engineRepository, cardRepository, nextRound, updateCard, stack),
        getRound = GetRound(engineRepository),
        getReverse = GetReverse(engineRepository),
        wait = null,
        import = ImportCards(repository = cardRepository, stack = stack),
        export = ExportCards(cardRepository)
    )


    private val wait =
        Wait(engineRepository, updateCard, inicjatywkaUseCases.nextTurn, stack)

    private val undoRedoViewModel = UndoRedoViewModel(
        undo = inicjatywkaUseCases.undoAction,
        redo = inicjatywkaUseCases.redoAction,
        stack = stack
    )

    private val debugViewModel = DebugViewModel(inicjatywkaUseCases)

    init {
        actions.actionUseCaseActionList = actionUseCaseActionList
        inicjatywkaUseCases.wait = wait
    }

    fun getUndoRedoViewModel(): UndoRedoViewModel {
        return undoRedoViewModel
    }

    fun getDebugViewModel(): DebugViewModel {
        return debugViewModel
    }

    fun getCardListViewModel(): CardListViewModel {
        return CardListViewModel(
            addCard = inicjatywkaUseCases.addCard,
            deleteCard = inicjatywkaUseCases.deleteCard,
            getCards = inicjatywkaUseCases.getCards
        )
    }

    fun getCardEditViewModel(): CardEditViewModel {
        return CardEditViewModel(
            updateCard = inicjatywkaUseCases.updateCard
        )
    }
}