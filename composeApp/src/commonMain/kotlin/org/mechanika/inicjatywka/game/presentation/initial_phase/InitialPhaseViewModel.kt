package org.mechanika.inicjatywka.game.presentation.initial_phase


import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.presentation.components.card.CardEvent
import org.mechanika.inicjatywka.game.presentation.components.card.CardViewModel
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

class InitialPhaseViewModel(
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    val undoRedoViewModel: UndoRedoViewModel,
    val debugViewModel: DebugViewModel,
    val selectedCardViewModel: CardViewModel,
    private val onNavigateToInitiativePhase: () -> Unit
) : ViewModel(), ComponentContext by componentContext {

    val state = InitialPhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Engine.Phases.Initiative) {
                    onNavigateToInitiativePhase()
                }
                it
            },
        cards = inicjatywkaUseCases.getCards()
            .onEach {
                val card = it.find { card -> card.id == selectedCardViewModel.cardEdit?.id }
                selectedCardViewModel.onEvent(CardEvent.EditCard(card))
            }
    )


    fun onEvent(event: InitialPhaseEvent) {
        when (event) {
            InitialPhaseEvent.StartInitiative -> {
                runBlocking { inicjatywkaUseCases.startInitiative() }
                onNavigateToInitiativePhase()
            }

            is InitialPhaseEvent.Export -> inicjatywkaUseCases.export(event.path)
            is InitialPhaseEvent.Import -> inicjatywkaUseCases.import(event.path)

            is InitialPhaseEvent.OnCardSelect -> selectedCardViewModel.onEvent(
                CardEvent.EditCard(event.card)
            )

            is InitialPhaseEvent.OnCardSave -> selectedCardViewModel.onEvent(
                CardEvent.SaveCard(event.card)
            )

            is InitialPhaseEvent.OnStatUpdate -> selectedCardViewModel.onEvent(
                CardEvent.UpdateCardStat(event.id, event.value)
            )

            is InitialPhaseEvent.OnCardDelete -> {
                selectedCardViewModel.onEvent(CardEvent.EditCard(null))
                inicjatywkaUseCases.deleteCard(event.cardId)
            }

            InitialPhaseEvent.OnCardAdd -> inicjatywkaUseCases.addCard()
        }
    }
}