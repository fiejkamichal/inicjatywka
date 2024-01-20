package org.mechanika.inicjatywka.game.presentation.initiative_phase

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
import org.mechanika.inicjatywka.game.domain.model.phase.Phase
import org.mechanika.inicjatywka.game.presentation.components.debug.Debug
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Redo
import org.mechanika.inicjatywka.game.presentation.components.undoredo.Undo

@Composable
fun InitiativePhaseScreen(
    component: InitiativePhaseViewModel
) {
    val currentPhase = component.state.currentPhase.collectAsState(Phase.Phases.Initiative)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                component.onEvent(InitiativePhaseEvent.StopInitiative)
            }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Koniec Inicjatywy")
            }
        }
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            item {
                Undo (
                    undoRedoViewModel =  component.undoRedoViewModel
                )
                Redo ( component.undoRedoViewModel )
            }
            item {
                Text(
                    text = "Gra z inicjatywą",
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