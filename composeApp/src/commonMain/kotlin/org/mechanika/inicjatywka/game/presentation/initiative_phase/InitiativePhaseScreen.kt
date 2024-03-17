package org.mechanika.inicjatywka.game.presentation.initiative_phase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.presentation.components.card.CardEdit
import org.mechanika.inicjatywka.game.presentation.components.card.CardEditEvent
import org.mechanika.inicjatywka.game.presentation.components.card.CardListEvent
import org.mechanika.inicjatywka.game.presentation.components.card.InitiativeCardList
import org.mechanika.inicjatywka.game.presentation.components.card.New
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.layout.Layout
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitiativePhaseScreen(
    component: InitiativePhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Engine.Phases.Initiative)
    val currentCardId = component.state.currentCardId.collectAsState(null)
    val sortedCards = component.cardListViewModel.state.cards.collectAsState(emptyList())
    val round = component.state.round.collectAsState(0)
    val reverse = component.state.reverse.collectAsState(false)

    Layout(
        floatingActionButton = {
            DebugButton(component.debugViewModel)
        },
        topLeftText = "Gra z inicjatywą. Runda: " + round.value + " " + if (!reverse.value) "Do przodu" else "do tyłu",
        topRightText = /*currentPhase.value.toString() +*/ " Obecna tura: " + currentCardId.value.toString(),
        topMiddleContent = {
            Row {
                Undo(component.undoRedoViewModel)
                Redo(component.undoRedoViewModel)
            }
        },
        middleLeftContent = {
            Column {
                CardEdit(
                    modifier = Modifier.weight(0.9f),
                    cardEdit = component.currentCardEditViewModel.cardEdit,
                    onUpdate = { id, value ->
                        component.currentCardEditViewModel.onEvent(
                            CardEditEvent.UpdateCardStat(
                                id,
                                value
                            )
                        )
                    },
                    onSave = { card ->
                        component.currentCardEditViewModel.onEvent(
                            CardEditEvent.SaveCard(
                                card
                            )
                        )
                    }
                )
                Row (
                    modifier = Modifier.weight(0.1f)
                ) {
                    Button(
                        onClick = { component.onEvent(InitiativePhaseEvent.NextTurn) }
                    ) {
                        Text("Koniec tury postaci")
                    }
                    Button(
                        onClick = { component.onEvent(InitiativePhaseEvent.Wait) },
                        enabled = !reverse.value
                    ) {
                        Text("Postać czeka")
                    }
                }
            }
        },
        middleMiddleContent = {
            InitiativeCardList(
                sortedCards = sortedCards.value,
                highlightedCardId = currentCardId.value,
                onCardSelect = {
                    component.selectedCardEditViewModel.onEvent(
                        CardEditEvent.EditCard(
                            it
                        )
                    )
                }
            )
        },
        middleRightContent = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CardEdit(
                    modifier = Modifier.weight(0.9f),
                    cardEdit = component.selectedCardEditViewModel.cardEdit,
                    onUpdate = { id, value ->
                        component.selectedCardEditViewModel.onEvent(
                            CardEditEvent.UpdateCardStat(
                                id,
                                value
                            )
                        )
                    },
                    onSave = { card ->
                        component.selectedCardEditViewModel.onEvent(
                            CardEditEvent.SaveCard(
                                card
                            )
                        )
                    },
                )
                Row (
                    modifier = Modifier.weight(0.1f)
                ) {
                    New { component.cardListViewModel.onEvent(CardListEvent.NewCard) }
                }
            }
        },
        bottomContent = {
            Button(onClick = {
                component.onEvent(InitiativePhaseEvent.StopInitiative)
            }) {
                Text(
                    "Koniec Inicjatywy"
                )
            }

        },
        bottomSheet = {
            DebugBottomSheet(
                isOpen = component.debugViewModel.isDebugSheetOpen.isDebugSheetOpen,
                viewModel = component.debugViewModel,
                modifier = Modifier
            )
        }
    )
}

