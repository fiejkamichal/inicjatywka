package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo.UndoRedoViewModel

class InitiativePhaseViewModel (
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    private val onNavigateToInitialPhaseViewModel: () -> Unit
): ViewModel(), ComponentContext by componentContext {

    val undoRedoViewModel = UndoRedoViewModel(
        undo = inicjatywkaUseCases.undoAction,
        redo = inicjatywkaUseCases.redoAction,
        stack = inicjatywkaUseCases.stack
    )

    val debugViewModel = DebugViewModel(
        inicjatywkaUseCases
    )

    val state = InitiativePhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Phase.Phases.Initial) {
                    onNavigateToInitialPhaseViewModel()
                }
                it
            }
    )

    fun onEvent(event: InitiativePhaseEvent){
        when(event) {
            InitiativePhaseEvent.StopInitiative -> {
                runBlocking { inicjatywkaUseCases.stopInitiative() }
                onNavigateToInitialPhaseViewModel()
            }
        }
    }
}