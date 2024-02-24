package org.mechanika.inicjatywka.game.presentation.initial_phase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.engine.Engine
import org.mechanika.inicjatywka.game.presentation.components.card.CardList
import org.mechanika.inicjatywka.game.presentation.components.card.New
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugBottomSheet
import org.mechanika.inicjatywka.game.presentation.components.debug.DebugButton
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitialPhaseScreen(
    component: InitialPhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Engine.Phases.Initial)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                component.onEvent(InitialPhaseEvent.StartInitiative)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Start Inicjatywy"
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
                    text = "Ekran startowy",
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
                New(component.cardViewModel)
            }
            item {
                CardList(component.cardViewModel)
            }
        }
    }
    DebugBottomSheet(
        isOpen = component.debugViewModel.isDebugSheetOpen.isDebugSheetOpen,
        viewModel = component.debugViewModel,
        modifier = Modifier
    )
}