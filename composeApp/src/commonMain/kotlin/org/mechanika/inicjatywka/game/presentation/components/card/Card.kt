package org.mechanika.inicjatywka.game.presentation.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.mechanika.inicjatywka.game.domain.model.card.Card
import org.mechanika.inicjatywka.game.presentation.components.card.ally.AllySwitch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Card(
    modifier: Modifier = Modifier,
    card: Card?,
    onUpdate: (id: Card.Stat.Id, value: String) -> Unit,
    onSave: (card: Card) -> Unit,
    onDelete: ((cardId: Long) -> Unit)? = null,
    highlight: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (highlight)
                    if (card?.getStat(Card.Stat.Id.Ally)?.value.toBoolean())
                        Color.Green
                    else
                        Color.Red
                else Color.DarkGray
            )
            .padding(10.dp)
    ) {
        val stateVertical = rememberScrollState(0)
        val stateHorizontal = rememberScrollState(0)

        if (card != null) {
            Column(
                modifier = Modifier
                    .background(
                        if (card.getStat(Card.Stat.Id.Ally).value.toBoolean()) Color(
                            0xFF94DF1A
                        ) else Color(0xFFDF291E)
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Save {
                        onSave(card)
                    }
                    onDelete?.let { delete ->
                        Delete(
                            card.id != null
                        ) { card.id?.let { delete(it) } }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    StatEdit(
                        stat = card.getStat(Card.Stat.Id.Initiative),
                        onValueChanged = onUpdate
                    )
                    StatEdit(
                        modifier = Modifier.weight(1f),
                        stat = card.getStat(Card.Stat.Id.Name),
                        onValueChanged = onUpdate
                    )
                    StatEdit(
                        stat = card.getStat(Card.Stat.Id.Ally),
                        onValueChanged = onUpdate
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.HitPoints),
                                onValueChanged = onUpdate
                            )
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.MaxHitPoints),
                                onValueChanged = onUpdate
                            )
                        }
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.Mana),
                                onValueChanged = onUpdate
                            )
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.MaxMana),
                                onValueChanged = onUpdate
                            )
                        }
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.Resilience),
                                onValueChanged = onUpdate
                            )
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.MaxResilience),
                                onValueChanged = onUpdate
                            )
                        }
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.Concentration),
                                onValueChanged = onUpdate
                            )
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.MaxConcentration),
                                onValueChanged = onUpdate
                            )
                        }
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.MovePoints),
                                onValueChanged = onUpdate
                            )
                            StatEdit(
                                stat = card.getStat(Card.Stat.Id.Steps),
                                onValueChanged = onUpdate
                            )
                        }
                    }
                    StatEdit(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        stat = card.getStat(Card.Stat.Id.States),
                        onValueChanged = onUpdate
                    )
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
    modifier: Modifier = Modifier,
    stat: Card.Stat,
    onValueChanged: (Card.Stat.Id, String) -> Unit
) {
    when (stat.id) {
        Card.Stat.Id.Null -> {}
        Card.Stat.Id.Name,
        Card.Stat.Id.States -> StatTextField(
            modifier = modifier,
            value = stat.value,
            label = stat.name,
            onValueChanged = {
                onValueChanged(stat.id, it)
            }
        )

        Card.Stat.Id.Initiative,
        Card.Stat.Id.HitPoints,
        Card.Stat.Id.MaxHitPoints,
        Card.Stat.Id.Resilience,
        Card.Stat.Id.MaxResilience,
        Card.Stat.Id.Mana,
        Card.Stat.Id.MaxMana,
        Card.Stat.Id.Concentration,
        Card.Stat.Id.MaxConcentration,
        Card.Stat.Id.MovePoints,
        Card.Stat.Id.Steps -> StatLongField(
            modifier = modifier,
            value = stat.value.toLong(),
            fieldName = stat.name,
            onValueChanged = {
                onValueChanged(stat.id, it.toString())
            }
        )

        Card.Stat.Id.Ally -> AllySwitch(
            modifier = modifier,
            value = stat.value.toBoolean(),
            onValueChanged = {
                onValueChanged(stat.id, it.toString())
            }
        )
    }
}

@Composable
private fun StatTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .widthIn(200.dp, 300.dp),
        value = value,
        label = { Text(label) },
        onValueChange = onValueChanged
    )
}

@Composable
fun StatLongField(
    modifier: Modifier = Modifier,
    value: Long,
    fieldName: String,
    onValueChanged: (Long) -> Unit
) {
    val pattern = remember { Regex("^-?\\d*\$") }
    OutlinedTextField(
        modifier = modifier
            .width(100.dp)
            .padding(0.dp),
        label = { Text(fieldName) },
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

