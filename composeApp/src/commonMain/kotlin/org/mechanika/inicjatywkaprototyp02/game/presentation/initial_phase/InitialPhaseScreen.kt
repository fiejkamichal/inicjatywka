package org.mechanika.inicjatywkaprototyp02.game.presentation.initial_phase

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
import org.mechanika.inicjatywkaprototyp02.game.domain.model.phase.Phase
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug.Debug
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo.Undo

@Composable
fun InitialPhaseScreen(
    component: InitialPhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Phase.Phases.Initial)

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
                Undo (
                    undoRedoViewModel =  component.undoRedoViewModel
                )
                Redo ( component.undoRedoViewModel )
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
                Debug(component.debugViewModel)
            }
        }
    }
}