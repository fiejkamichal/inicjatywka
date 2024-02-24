package org.mechanika.inicjatywka.game.presentation.components.card

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
import org.mechanika.inicjatywka.game.domain.model.card.Card

@Composable
fun CardEdit(
    cardEdit: Card?,
    onUpdate: (id: Card.Stat.Id, value: String) -> Unit,
    onSave: (card: Card) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(4.dp, Color.Black)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text("Edycja Karta postaci (${cardEdit?.id}):")
            cardEdit?.getStats()?.forEach {
                StatEdit(
                    it,
                    onValueChanged = onUpdate
                )
            }
            Button(
                onClick = {
                    cardEdit?.let {
                        onSave(it)
                    }
                },
            ) {
                Text("Zapisz")
            }
        }
    }
}

@Composable
fun StatEdit(
    stat: Card.Stat,
    onValueChanged: (Card.Stat.Id, String) -> Unit
) {
    Row {
        when (stat.id) {
            Card.Stat.Id.Null -> {}
            Card.Stat.Id.Name,
            Card.Stat.Id.States -> StatTextField(
                value = stat.value,
                placeholder = stat.name,
                onValueChanged = {
                    onValueChanged(stat.id, it)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Card.Stat.Id.Initiative,
            Card.Stat.Id.HitPoints,
            Card.Stat.Id.Resilience,
            Card.Stat.Id.Mana,
            Card.Stat.Id.Concentration,
            Card.Stat.Id.MovePoints,
            Card.Stat.Id.Steps -> StatLongField(
                value = stat.value.toLong(),
                fieldName = stat.name,
                onValueChanged = {
                    onValueChanged(stat.id, it.toString())
                },
                modifier = Modifier.fillMaxWidth()
            )

            Card.Stat.Id.Waits,
            Card.Stat.Id.Ally -> StatBooleanField(
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