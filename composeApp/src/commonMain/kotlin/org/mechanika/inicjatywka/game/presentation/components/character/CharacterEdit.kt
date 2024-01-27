package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.character.CharacterCard

@Composable
fun CharacterEdit(
//    card: CharacterCard,
    viewModel: CharacterViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(4.dp, Color.Black)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Edycja Karta postaci (${viewModel.characterCardEdit?.id}):")
            viewModel.characterCardEdit?.getStats()?.forEach { it ->
                StatEdit(
                    it,
                    onValueChanged = { newStat ->
                        viewModel.onEvent(CharacterEvent.UpdateCharacterStat(newStat))
                    }
                )
            }
            Button(
                onClick = {
                    viewModel.characterCardEdit?.let {
                        CharacterEvent.SaveCharacter(
                            it
                        )
                    }?.let { viewModel.onEvent(it) }
                },
            ) {
                Text("Zapisz")
            }
        }
    }
}

@Composable
fun StatEdit(
    stat: CharacterCard.Stat,
    onValueChanged: (CharacterCard.Stat) -> Unit
) {
    Row {
        StatTextField(
            value = stat.value,
            placeholder = stat.name,
            onValueChanged = {
                onValueChanged(CharacterCard.Stat(stat.name, it))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StatTextField(
    value: String,
    placeholder: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth()
        )
    }
}