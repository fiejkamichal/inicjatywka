package org.mechanika.inicjatywka

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import org.mechanika.inicjatywka.game.presentation.initial_phase.InitialPhaseScreen
import org.mechanika.inicjatywka.game.presentation.initiative_phase.InitiativePhaseScreen
import org.mechanika.inicjatywka.navigation.RootComponent


@Composable
fun App(
    root: RootComponent
) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()

        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when (val instance = child.instance) {
                is RootComponent.Child.InitialPhase ->
                    InitialPhaseScreen(
                        instance.component
                    )

                is RootComponent.Child.InitiativePhase ->
                    InitiativePhaseScreen(
                        instance.component
                    )
            }
        }
    }
}