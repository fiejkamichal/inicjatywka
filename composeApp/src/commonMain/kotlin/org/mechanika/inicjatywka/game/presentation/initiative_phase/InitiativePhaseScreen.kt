package org.mechanika.inicjatywka.game.presentation.initiative_phase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.presentation.components.card.CardEdit
import org.mechanika.inicjatywka.game.presentation.components.card.InitiativeCardList
import org.mechanika.inicjatywka.game.presentation.components.card.New
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.layout.Layout
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

@Composable
fun InitiativePhaseScreen(
    state: InitiativePhaseState,
    onEvent: (InitiativePhaseEvent) -> Unit,
    debugViewModel: DebugViewModel,
    undoRedoViewModel: UndoRedoViewModel,
    selectedCard: Card?,
    currentCard: Card?,
) {
    val currentPhase = state.currentPhase.collectAsState(Engine.Phases.Initiative)
    val sortedCards = state.cards.collectAsState(emptyList())
    val round = state.round.collectAsState(0)
    val reverse = state.reverse.collectAsState(false)
    val currentCardId = state.currentCardId.collectAsState(null)

    Layout(
        floatingActionButton = {
            DebugButton(debugViewModel)
        },
        topLeftText = "Gra z inicjatywą. Runda: " + round.value + " " + if (!reverse.value) "Do przodu" else "do tyłu",
        topRightText = "Obecna tura: " + currentCardId.value.toString(),
        topMiddleContent = {
            Row {
                Undo(undoRedoViewModel)
                Redo(undoRedoViewModel)
            }
        },
        middleLeftContent = {
            Column {
                CardEdit(
                    modifier = Modifier.weight(0.9f),
                    highlight = true,
                    cardEdit = currentCard,
                    onUpdate = { id, value ->
                        onEvent(InitiativePhaseEvent.OnCurrentStatUpdate(id, value))
                    },
                    onSave = { onEvent(InitiativePhaseEvent.OnCurrentCardSave(it)) }
                )
                Row(
                    modifier = Modifier.weight(0.1f)
                ) {
                    Button(
                        onClick = { onEvent(InitiativePhaseEvent.NextTurn) }
                    ) {
                        Text("Koniec tury postaci")
                    }
                    Button(
                        onClick = { onEvent(InitiativePhaseEvent.Wait) },
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
                highlightedCardId = currentCard?.id,
                selectedCardId = selectedCard?.id,
                onCardSelect = { onEvent(InitiativePhaseEvent.OnCardSelect(it)) },
            )
        },
        middleRightContent = {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                CardEdit(
                    modifier = Modifier.weight(0.9f),
                    cardEdit = selectedCard,
                    onUpdate = { id, value ->
                        onEvent(InitiativePhaseEvent.OnSelectedStatUpdate(id, value))
                    },
                    onSave = { onEvent(InitiativePhaseEvent.OnSelectedCardSave(it)) }
                )
                Row(
                    modifier = Modifier.weight(0.1f)
                ) {
                    New { onEvent(InitiativePhaseEvent.OnCardAdd) }
                }
            }
        },
        bottomContent = {
            Button(onClick = {
                onEvent(InitiativePhaseEvent.StopInitiative)
            }) {
                Text(
                    "Koniec Inicjatywy"
                )
            }

        },
        bottomSheet = {
            DebugBottomSheet(
                isOpen = debugViewModel.isDebugSheetOpen.isDebugSheetOpen,
                viewModel = debugViewModel,
                modifier = Modifier
            )
        }
    )
}

