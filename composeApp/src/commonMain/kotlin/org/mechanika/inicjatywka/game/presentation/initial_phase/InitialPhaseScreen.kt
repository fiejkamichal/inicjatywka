package org.mechanika.inicjatywka.game.presentation.initial_phase

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
import org.mechanika.inicjatywka.game.presentation.components.card.Card
import org.mechanika.inicjatywka.game.presentation.components.card.InitiativeCardList
import org.mechanika.inicjatywka.game.presentation.components.card.New
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugViewModel
import org.mechanika.inicjatywka.game.presentation.components.importExport.Export
import org.mechanika.inicjatywka.game.presentation.components.importExport.Import
import org.mechanika.inicjatywka.game.presentation.components.layout.Layout
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.UndoRedoViewModel

@Composable
fun InitialPhaseScreen(
    state: InitialPhaseState,
    onEvent: (InitialPhaseEvent) -> Unit,
    debugViewModel: DebugViewModel,
    undoRedoViewModel: UndoRedoViewModel,
    selectedCard: Card?,
) {
    val sortedCards = state.cards.collectAsState(emptyList())
    val currentPhase = state.currentPhase.collectAsState(Engine.Phases.Initial)

    Layout(
        floatingActionButton = {
            DebugButton(debugViewModel)
        },
        topLeftText = "Ekran startowy",
        //topRightText = currentPhase.value.toString(),
        topMiddleContent = {
            Row {
                Undo(undoRedoViewModel)
                Redo(undoRedoViewModel)
            }
        },
        middleMiddleContent = {
            InitiativeCardList(
                sortedCards = sortedCards.value,
                selectedCardId = selectedCard?.id,
                highlightedCardId = null,
                onCardSelect = { onEvent(InitialPhaseEvent.OnCardSelect(it)) }
            )
        },
        middleRightContent =
        {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    card = selectedCard,
                    onUpdate = { id, value ->
                        onEvent(InitialPhaseEvent.OnStatUpdate(id, value))
                    },
                    onSave = { onEvent(InitialPhaseEvent.OnCardSave(it)) },
                    onDelete = { cardId -> onEvent(InitialPhaseEvent.OnCardDelete(cardId)) }
                )
                Row {
                    New { onEvent(InitialPhaseEvent.OnCardAdd) }
                }
            }

        },
        bottomContent = {
            Row {
                Button(onClick = {
                    onEvent(InitialPhaseEvent.StartInitiative)
                }) {
                    Text("Start Inicjatywy")
                }
                Export(onExport = { onEvent(InitialPhaseEvent.Export(it)) })
                Import(onImport = { onEvent(InitialPhaseEvent.Import(it)) })
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