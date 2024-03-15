package org.mechanika.inicjatywka.game.presentation.initial_phase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.presentation.components.card.CardList
import org.mechanika.inicjatywka.game.presentation.components.card.CardListEvent
import org.mechanika.inicjatywka.game.presentation.components.card.New
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.layout.Layout
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitialPhaseScreen(
    component: InitialPhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Engine.Phases.Initial)

    Layout(
        floatingActionButton = {
            DebugButton(component.debugViewModel)
        },
        topContent = {
            Row {
                Undo(component.undoRedoViewModel)
                Redo(component.undoRedoViewModel)
                Text(
                    text = "Ekran startowy",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentPhase.value.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        middleContent =
        {
            Row(){
                Button(onClick = {
                    component.onEvent(InitialPhaseEvent.StartInitiative)
                }) {
                    Text("Start Inicjatywy")
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        New { component.cardListViewModel.onEvent(CardListEvent.NewCard) }
                    }
                    item {
                        CardList(component.cardListViewModel, component.cardEditViewModel)
                    }
                }
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