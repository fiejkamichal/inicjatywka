package org.mechanika.inicjatywka.game.presentation.initiative_phase

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.card.Card.Stat
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.presentation.components.card.CardEdit
import org.mechanika.inicjatywka.game.presentation.components.card.CardEvent
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitiativePhaseScreen(
    component: InitiativePhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Engine.Phases.Initiative)
    val currentCardId = component.state.currentCardId.collectAsState(null)
    val sortedCards = component.cardViewModel.state.cards.collectAsState(emptyList())
    val round = component.state.round.collectAsState(0)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                component.onEvent(InitiativePhaseEvent.StopInitiative)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Koniec Inicjatywy"
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                DebugButton(component.debugViewModel)
                Undo(component.undoRedoViewModel)
                Redo(component.undoRedoViewModel)
            }
            item {
                Text(
                    text = "Gra z inicjatywą. Runda: " + round.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Text(
                    text = currentPhase.value.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            item {
                Text(
                    text = "Obecna tura: " + currentCardId.value.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { component.onEvent(InitiativePhaseEvent.NextTurn) }
                ) {
                    Text("Koniec tury postaci")
                }
                Button(
                    onClick = { component.onEvent(InitiativePhaseEvent.Wait) }
                ) {
                    Text("Postać czeka")
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    sortedCards.value.forEach {
                        Column(
                            modifier = Modifier.border(
                                width = 1.dp,
                                color = if (it.getStat(Stat.Id.Waits).value.toBoolean())
                                    Color.Red
                                else Color.Black
                            ),
                        ) {
                            Text(
                                text = it.getStat(Stat.Id.Initiative).value + " " +
                                        it.getStat(Stat.Id.Name).value + " " + it.id.toString(),
                                fontWeight = (
                                        if (it.id == currentCardId.value)
                                            FontWeight.Bold
                                        else
                                            FontWeight.Light
                                        )
                            )
                        }
                    }
                }
            }
            item {
                CardEdit(

                    cardEdit = component.cardViewModel.cardEdit,
                    onUpdate = { id, value ->
                        component.cardViewModel.onEvent(CardEvent.UpdateCardStat(id, value))
                    },
                    onSave = { card ->
                        component.cardViewModel.onEvent(CardEvent.SaveCard(card))
                    }
                )
            }
        }
    }
    DebugBottomSheet(
        isOpen = component.debugViewModel.isDebugSheetOpen.isDebugSheetOpen,
        viewModel = component.debugViewModel,
        modifier = Modifier
    )
}