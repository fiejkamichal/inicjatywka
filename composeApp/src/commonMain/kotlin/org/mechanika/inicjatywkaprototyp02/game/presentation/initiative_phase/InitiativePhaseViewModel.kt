package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.model.Phase
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.PhaseUseCases

class InitiativePhaseViewModel (
    private val phaseUseCases: PhaseUseCases,
    componentContext: ComponentContext,
    private val onNavigateToInitialPhaseViewModel: () -> Unit
): ViewModel(), ComponentContext by componentContext {
    private val _state = mutableStateOf(InitiativePhaseState())
    val state: State<InitiativePhaseState> = _state

    init {
        getPhase()
    }

    fun onEvent(event: InitiativePhaseEvent){
        when(event) {
            InitiativePhaseEvent.StopInitiative -> {
                runBlocking { phaseUseCases.stopInitiative() }
                onNavigateToInitialPhaseViewModel()
            }
        }
    }

    private fun getPhase(){
        runBlocking{
            val phase = phaseUseCases.getPhase()
            _state.value = state.value.copy(
                currentPhase = phase
            )
        }
    }
}