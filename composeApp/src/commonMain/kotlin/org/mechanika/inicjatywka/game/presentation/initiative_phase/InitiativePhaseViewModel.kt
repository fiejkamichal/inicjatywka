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
    val cardViewModel: CardViewModel,
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
            .onEach { it ->
                val card = it?.let { it1 -> inicjatywkaUseCases.getCard(it1) }
                card?.let { it1 ->
                    cardViewModel.onEvent(CardEvent.EditCard(it1))
                }
            },
        round = inicjatywkaUseCases.getRound()
    )

    fun onEvent(event: InitiativePhaseEvent) {
        when (event) {
            InitiativePhaseEvent.StopInitiative -> {
                inicjatywkaUseCases.stopInitiative()
                onNavigateToInitialPhase()
            }

            InitiativePhaseEvent.NextTurn -> inicjatywkaUseCases.nextTurn()
            InitiativePhaseEvent.Wait -> cardViewModel.cardEdit?.let {
                inicjatywkaUseCases.wait?.let { it1 -> it1(it) }
                    ?: error("wait is not available!!!")
            }
        }
    }
}