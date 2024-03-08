package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    val deletedCards = component.state.deletedCards.collectAsState(emptyList())
    val currentStackPosition = component.state.currentStackPosition.collectAsState(null)
    val currentCardId = component.state.currentCardId.collectAsState(null)
    val currentCardIds = component.state.currentCardIds.collectAsState(emptyList())
    val actionListActionIds = component.state.actionListActionIds.collectAsState(emptyList())
    val actionListItems = component.state.actionListItems.collectAsState(emptyList())
    val allActions = component.state.allActions.collectAsState(emptyList())

    Column {
        Button(
            onClick = { component.updateState() }
        ) {
            Text(
                "Update"
            )
        }

        Text(
            text = "Faza (${currentPhase.value})",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)

        )

        Text(
            text = "Obecna Pozycja na Stosie (${currentStackPosition.value})",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Text(
            text = "Obecna Karta Postaci (${currentCardId.value})",
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        allActions.value.forEachIndexed { index, strings ->
            DebugList(strings, "table $index")
        }

        DebugList(actionListActionIds.value, "actionListActionIds")
        DebugList(actionListItems.value, "actionListItems")
        DebugList(currentCardIds.value, "current card ids")
        DebugList(phases.value, "phases")
        DebugList(cards.value, "cards")
        DebugList(deletedCards.value, "deleted cards")
        DebugList(actions.value, "actions")
        DebugList(phaseChanges.value, "phaseChanges")
    }
}