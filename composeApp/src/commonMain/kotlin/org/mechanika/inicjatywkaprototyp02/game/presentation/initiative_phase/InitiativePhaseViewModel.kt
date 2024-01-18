package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.runBlocking
import org.mechanika.inicjatywkaprototyp02.game.domain.use_case.InicjatywkaUseCases
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug.DebugViewModel

class InitiativePhaseViewModel (
    private val inicjatywkaUseCases: InicjatywkaUseCases,
    componentContext: ComponentContext,
    private val onNavigateToInitialPhaseViewModel: () -> Unit
): ViewModel(), ComponentContext by componentContext {

    val debugViewModel = DebugViewModel(
        inicjatywkaUseCases
    )

    val state = InitiativePhaseState(
        currentPhase = inicjatywkaUseCases.getPhase()
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