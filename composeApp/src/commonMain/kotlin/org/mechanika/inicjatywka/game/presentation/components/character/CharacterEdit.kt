package org.mechanika.inicjatywka.game.presentation.components.character

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
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
                    onValueChanged = { id, value ->
                        viewModel.onEvent(CharacterEvent.UpdateCharacterStat(id, value))
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
    onValueChanged: (CharacterCard.Stat.Id, String) -> Unit
) {
    Row {
        when (stat.id) {
            CharacterCard.Stat.Id.Null -> {}
            CharacterCard.Stat.Id.Name,
            CharacterCard.Stat.Id.States -> StatTextField(
                value = stat.value,
                placeholder = stat.name,
                onValueChanged = {
                    onValueChanged(stat.id, it)
                },
                modifier = Modifier.fillMaxWidth()
            )

            CharacterCard.Stat.Id.Initiative,
            CharacterCard.Stat.Id.HitPoints,
            CharacterCard.Stat.Id.Resilience,
            CharacterCard.Stat.Id.Mana,
            CharacterCard.Stat.Id.Concentration,
            CharacterCard.Stat.Id.MovePoints,
            CharacterCard.Stat.Id.Steps -> StatLongField(
                value = stat.value.toLong(),
                fieldName = stat.name,
                onValueChanged = {
                    onValueChanged(stat.id, it.toString())
                },
                modifier = Modifier.fillMaxWidth()
            )

            CharacterCard.Stat.Id.Waits,
            CharacterCard.Stat.Id.Ally -> StatBooleanField(
                value = stat.value.toBoolean(),
                fieldName = stat.name,
                onValueChanged = {
                    onValueChanged(stat.id, it.toString())
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
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

@Composable
private fun StatBooleanField(
    value: Boolean,
    fieldName: String,
    onValueChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row {
            Text(text = fieldName)
            Checkbox(
                checked = value,
                onCheckedChange = {
                    onValueChanged(it)
                }
            )
        }
    }
}

@Composable
private fun StatLongField(
    value: Long,
    fieldName: String,
    onValueChanged: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val pattern = remember { Regex("^-?\\d*\$") }
    Column(modifier) {
        Row {
            Text(text = fieldName)
            OutlinedTextField(
                value = value.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    if (it.matches(pattern)) {
                        onValueChanged(it.toLongOrNull() ?: 0)
                    }
                }
            )
            Button(
                onClick = { onValueChanged(value + 1) }
            ) {
                Text("+")
            }
            Button(
                onClick = { onValueChanged(value - 1) }
            ) {
                Text("-")
            }
        }
    }
}