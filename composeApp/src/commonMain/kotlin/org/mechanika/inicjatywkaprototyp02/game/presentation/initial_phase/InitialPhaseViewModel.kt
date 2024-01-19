package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase


import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo.UndoRedoViewModel

class InitialPhaseViewModel(
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    private val onNavigateToInitiativePhaseViewModel: () -> Unit
): ViewModel(), ComponentContext by componentContext {

    val undoRedoViewModel = UndoRedoViewModel(
        undo = inicjatywkaUseCases.undoAction,
        redo = inicjatywkaUseCases.redoAction,
        stack = inicjatywkaUseCases.stack
    )

    val debugViewModel = DebugViewModel(
        inicjatywkaUseCases
    )

    val state = InitialPhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Phase.Phases.Initiative) {
                    onNavigateToInitiativePhaseViewModel()
                }
                it
            }
    )

    fun onEvent(event: InitialPhaseEvent) {
        when(event) {
            InitialPhaseEvent.StartInitiative -> {
                runBlocking { inicjatywkaUseCases.startInitiative()}
                onNavigateToInitiativePhaseViewModel()
            }

            is InitialPhaseEvent.UndoRedo -> {
                undoRedoViewModel.onEvent(event.event)
            }
        }
    }
}

