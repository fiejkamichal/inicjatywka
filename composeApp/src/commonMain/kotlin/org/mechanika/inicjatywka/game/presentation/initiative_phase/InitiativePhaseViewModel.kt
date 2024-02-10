package org.mechanika.inicjatywka.game.presentation.initiative_phase

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywka.game.domain.model.engine.Phase
import org.mechanika.inicjatywka.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

class InitiativePhaseViewModel(
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    val undoRedoViewModel: UndoRedoViewModel,
    val debugViewModel: DebugViewModel,
    private val onNavigateToInitialPhase: () -> Unit
) : ViewModel(), ComponentContext by componentContext {

    val state = InitiativePhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
            .map {
                if (it == Phase.Phases.Initial) {
                    onNavigateToInitialPhase()
                }
                it
            }
    )

    fun onEvent(event: InitiativePhaseEvent) {
        when (event) {
            InitiativePhaseEvent.StopInitiative -> {
                runBlocking { inicjatywkaUseCases.stopInitiative() }
                onNavigateToInitialPhase()
            }
        }
    }
}