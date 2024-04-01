package org.mechanika.inicjatywka.game.presentation.initiative_phase

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.presentation.components.card.CardEvent
import org.mechanika.inicjatywka.game.presentation.components.card.CardViewModel
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

class InitiativePhaseViewModel(
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    val undoRedoViewModel: UndoRedoViewModel,
    val debugViewModel: DebugViewModel,
    val currentCardViewModel: CardViewModel,
    val selectedCardViewModel: CardViewModel,
    private val onNavigateToInitialPhase: () -> Unit
) : ViewModel(), ComponentContext by componentContext {

    val state = InitiativePhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Engine.Phases.Initial) {
                    onNavigateToInitialPhase()
                }
                it
            },
        currentCardId = inicjatywkaUseCases.getCurrentCardId()
            .onEach { cardId ->
                val card = cardId?.let { inicjatywkaUseCases.getCard(it) }
                currentCardViewModel.onEvent(CardEvent.EditCard(card))
                if (cardId == selectedCardViewModel.cardEdit?.id) {
                    selectedCardViewModel.onEvent(CardEvent.EditCard(null))
                }
            },
        round = inicjatywkaUseCases.getRound(),
        reverse = inicjatywkaUseCases.getReverse(),
        cards = inicjatywkaUseCases.getCards()
            .onEach {
                val selectedCard = it.find { card -> card.id == selectedCardViewModel.cardEdit?.id }
                selectedCardViewModel.onEvent(CardEvent.EditCard(selectedCard))
                val currentCard = it.find { card -> card.id == currentCardViewModel.cardEdit?.id }
                currentCardViewModel.onEvent(CardEvent.EditCard(currentCard))
            }
    )

    fun onEvent(event: InitiativePhaseEvent) {
        when (event) {
            InitiativePhaseEvent.StopInitiative -> {
                inicjatywkaUseCases.stopInitiative()
                onNavigateToInitialPhase()
            }

            InitiativePhaseEvent.NextTurn -> inicjatywkaUseCases.nextTurn()

            InitiativePhaseEvent.Wait -> currentCardViewModel.cardEdit?.let {
                inicjatywkaUseCases.wait?.let { it1 -> it1(it) }
                    ?: error("wait is not available!!!")
            }

            InitiativePhaseEvent.OnCardAdd -> inicjatywkaUseCases.addCard()
            is InitiativePhaseEvent.OnCardSelect -> {
                if (event.card.id != currentCardViewModel.cardEdit?.id)
                    selectedCardViewModel.onEvent(CardEvent.EditCard(event.card))
                else
                    selectedCardViewModel.onEvent(CardEvent.EditCard(null))
            }

            is InitiativePhaseEvent.OnCurrentCardSave -> currentCardViewModel.onEvent(
                CardEvent.SaveCard(event.card)
            )

            is InitiativePhaseEvent.OnCurrentStatUpdate -> currentCardViewModel.onEvent(
                CardEvent.UpdateCardStat(event.id, event.value)
            )

            is InitiativePhaseEvent.OnSelectedCardSave -> selectedCardViewModel.onEvent(
                CardEvent.SaveCard(event.card)
            )

            is InitiativePhaseEvent.OnSelectedStatUpdate -> selectedCardViewModel.onEvent(
                CardEvent.UpdateCardStat(event.id, event.value)
            )
        }
    }
}