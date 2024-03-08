package org.mechanika.inicjatywka.game.presentation.initial_phase


import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.presentation.components.card.CardEditViewModel
import org.mechanika.inicjatywka.game.presentation.components.card.CardListViewModel
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

class InitialPhaseViewModel(
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    val undoRedoViewModel: UndoRedoViewModel,
    val debugViewModel: DebugViewModel,
    val cardListViewModel: CardListViewModel,
    val cardEditViewModel: CardEditViewModel,
    private val onNavigateToInitiativePhase: () -> Unit
) : ViewModel(), ComponentContext by componentContext {

    val state = InitialPhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Engine.Phases.Initiative) {
                    onNavigateToInitiativePhase()
                }
                it
            }
    )

    fun onEvent(event: InitialPhaseEvent) {
        when (event) {
            InitialPhaseEvent.StartInitiative -> {
                runBlocking { inicjatywkaUseCases.startInitiative() }
                onNavigateToInitiativePhase()
            }
        }
    }
}

