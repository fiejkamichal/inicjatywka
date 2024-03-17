package org.mechanika.inicjatywka.game.presentation.initial_phase

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
import org.mechanika.inicjatywka.game.presentation.components.importExport.Export
import org.mechanika.inicjatywka.game.presentation.components.importExport.Import
import org.mechanika.inicjatywka.game.presentation.components.layout.Layout
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitialPhaseScreen(
    component: InitialPhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Engine.Phases.Initial)
    val currentCardId = component.state.currentCardId.collectAsState(null)
    val sortedCards = component.cardListViewModel.state.cards.collectAsState(emptyList())

    Layout(
        floatingActionButton = {
            DebugButton(component.debugViewModel)
        },
        topLeftText = "Ekran startowy",
        //topRightText = currentPhase.value.toString(),
        topMiddleContent = {
            Row {
                Undo(component.undoRedoViewModel)
                Redo(component.undoRedoViewModel)
            }
        },
        middleMiddleContent = {
            InitiativeCardList(
                sortedCards = sortedCards.value,
                highlightedCardId = currentCardId.value,
                onCardSelect = {
                    component.cardEditViewModel.onEvent(
                        CardEditEvent.EditCard(
                            it
                        )
                    )
                }
            )
        },
        middleRightContent =
        {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                CardEdit(
                    modifier = Modifier.weight(0.9f),
                    cardEdit = component.cardEditViewModel.cardEdit,
                    onUpdate = { id, value ->
                        component.cardEditViewModel.onEvent(
                            CardEditEvent.UpdateCardStat(
                                id,
                                value
                            )
                        )
                    },
                    onSave = { card ->
                        component.cardEditViewModel.onEvent(
                            CardEditEvent.SaveCard(
                                card
                            )
                        )
                    }
                )
                Row(
                    modifier = Modifier.weight(0.1f)
                ) {
                    New { component.cardListViewModel.onEvent(CardListEvent.NewCard) }
                }
            }

        },
        bottomContent = {
            Row {
                Button(onClick = {
                    component.onEvent(InitialPhaseEvent.StartInitiative)
                }) {
                    Text("Start Inicjatywy")
                }
                Export(onExport = { component.onEvent(InitialPhaseEvent.Export(it))} )
                Import(onImport = { component.onEvent(InitialPhaseEvent.Import(it))} )
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