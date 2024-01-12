package org.mechanika.inicjatywkaprototyp02.game.presentation.initiative_phase

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InitiativePhaseScreen(
    component: InitiativePhaseViewModel
) {
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
                    text = component.state.value.currentPhase.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}