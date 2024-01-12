package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase


import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.PhaseUseCases

class InitialPhaseViewModel(
    private val phaseUseCases: PhaseUseCases,
    componentContext: ComponentContext,
    private val onNavigateToInitiativePhaseViewModel: () -> Unit
): ViewModel(), ComponentContext by componentContext {
    private val _state = MutableStateFlow(InitialPhaseState())
    val state = _state.asStateFlow()

    init {
        getPhase()
    }

    fun onResume() {
        getPhase()
    }

    fun onEvent(event: InitialPhaseEvent) {
        when(event) {
            InitialPhaseEvent.StartInitiative -> {
                runBlocking { phaseUseCases.startInitiative()}
                onNavigateToInitiativePhaseViewModel()
            }
        }
    }

    fun getPhase(){
        runBlocking{
            val phase = phaseUseCases.getPhase()
            _state.value = state.value.copy(
                currentPhase = phase
            )
        }
    }
}