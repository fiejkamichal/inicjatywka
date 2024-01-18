package org.mechanika.inicjatywkaprototyp02.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.serialization.Serializable
import org.mechanika.inicjatywkaprototyp02.di.AppModule
import org.mechanika.inicjatywkaprototyp02.di.AppModulePlatform
import org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase.InitialPhaseViewModel
import org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase.InitiativePhaseViewModel

@OptIn(ExperimentalDecomposeApi::class)
class RootComponent(
    componentContext: ComponentContext,
    appModulePlatform: AppModulePlatform
    //getAppModulePlatform: () -> AppModulePlatform
): ComponentContext  by componentContext {

    private val appModule = AppModule(appModulePlatform)

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.InitialPhase,
        handleBackButton = false,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when(config) {
            Configuration.InitialPhase -> Child.InitialPhase(
                InitialPhaseViewModel(
                    appModule.inicjatywkaUseCases,
                    componentContext = context,
                    onNavigateToInitiativePhaseViewModel = {
                        navigation.pushNew(
                            Configuration.InitiativePhase
                        )
                    }
                )
            )
            is Configuration.InitiativePhase -> Child.InitiativePhase(
                InitiativePhaseViewModel(
                    appModule.inicjatywkaUseCases,
                    componentContext = context,
                    onNavigateToInitialPhaseViewModel = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    sealed class Child {
        data class InitialPhase(val component: InitialPhaseViewModel): Child()
        data class InitiativePhase(val component: InitiativePhaseViewModel): Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object InitialPhase: Configuration()
        @Serializable
        data object InitiativePhase: Configuration()
    }
}