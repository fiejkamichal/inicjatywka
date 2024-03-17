package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

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

        Text("Faza (${currentPhase.value})")

        Text("Obecna Pozycja na Stosie (${currentStackPosition.value})")

        Text("Obecna Karta Postaci (${currentCardId.value})")

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