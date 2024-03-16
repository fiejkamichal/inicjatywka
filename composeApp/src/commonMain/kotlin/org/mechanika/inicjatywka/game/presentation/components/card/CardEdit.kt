package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.card.Card

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardEdit(
    modifier: Modifier = Modifier,
    cardEdit: Card?,
    onUpdate: (id: Card.Stat.Id, value: String) -> Unit,
    onSave: (card: Card) -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.DarkGray)
            .padding(10.dp)
    ) {
        val stateVertical = rememberScrollState(0)
        val stateHorizontal = rememberScrollState(0)

        Column(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            FlowRow {
                cardEdit?.getStats()?.forEach {
                    StatEdit(
                        it,
                        onValueChanged = onUpdate
                    )
                }
            }
            Save {
                cardEdit?.let {
                    onSave(it)
                }
            }
        }
        /*
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(stateVertical)
        )
        HorizontalScrollbar(
            modifier = Modifier.align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(end = 12.dp),
            adapter = rememberScrollbarAdapter(stateHorizontal)
        )

         */
    }

}


@Composable
fun StatEdit(
    stat: Card.Stat,
    onValueChanged: (Card.Stat.Id, String) -> Unit
) {
    when (stat.id) {
        Card.Stat.Id.Null -> {}
        Card.Stat.Id.Name,
        Card.Stat.Id.States -> StatTextField(
            value = stat.value,
            placeholder = stat.name,
            onValueChanged = {
                onValueChanged(stat.id, it)
            }
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
            }
        )

        Card.Stat.Id.Waits,
        Card.Stat.Id.Ally -> StatBooleanField(
            value = stat.value.toBoolean(),
            fieldName = stat.name,
            onValueChanged = {
                onValueChanged(stat.id, it.toString())
            }
        )
    }
}

@Composable
private fun StatTextField(
    value: String,
    placeholder: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        placeholder = {
            Text(text = placeholder)
        },
        onValueChange = onValueChanged
    )
}

@Composable
private fun StatBooleanField(
    value: Boolean,
    fieldName: String,
    onValueChanged: (Boolean) -> Unit
) {
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

@Composable
fun StatLongField(
    value: Long,
    fieldName: String,
    onValueChanged: (Long) -> Unit
) {
    val pattern = remember { Regex("^-?\\d*\$") }
    OutlinedTextField(
        modifier = Modifier
            .padding(0.dp)
            .width(100.dp),
        label = {
            Text(
                text = fieldName,
                modifier = Modifier
                    .background(color = Color.LightGray)
            )
        },
        value = value.toString(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            if (it.matches(pattern)) {
                onValueChanged(it.toLongOrNull() ?: 0)
            }
        },
        trailingIcon = {
            Column {
                Button(
                    modifier = Modifier
                        .size(20.dp),
                    onClick = { onValueChanged(value + 1) },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("+")
                }
                Button(
                    modifier = Modifier
                        .size(20.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { onValueChanged(value - 1) }
                ) {
                    Text("-")
                }
            }
        }
    )
}

