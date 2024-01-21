package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Debug(
    component: DebugViewModel
) {
    val actions = component.state.actions.collectAsState(emptyList())
    val currentPhase = component.state.currentPhase.collectAsState(null)
    val phaseChanges = component.state.phaseChanges.collectAsState(emptyList())
    val phases = component.state.phases.collectAsState(emptyList())
    val cards = component.state.cards.collectAsState(emptyList())
    val currentStackPosition = remember { component.state.currentStackPosition }
    Column {
        Button(
            onClick = {component.updateState()}
        ) {
            Text(
                "Update"
            )
        }

        Row {
            Text(
                text = "Faza (${currentPhase.value})",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)

            )
        }

        Row {
            Text(
                text = "Obecna Pozycja na Stosie (${currentStackPosition.value})",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }

        DebugList(cards.value, "cards")
        DebugList(phases.value, "phases")
        DebugList(actions.value, "actions")
        DebugList(phaseChanges.value, "phaseChanges")
    }
}