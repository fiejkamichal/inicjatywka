package org.mechanika.inicjatywka.game.presentation.components.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    leftText: String = "",
    rightText: String = "",
    middleContent: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = leftText,
            modifier = Modifier
                .weight(40f)
                .border(1.dp, Color.Black)
                .padding(1.dp),
            textAlign = TextAlign.Center,

        )
        Row(
            modifier = Modifier
                .weight(20f)
                .border(1.dp, Color.Black)
                .padding(1.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            middleContent()
        }
        Text(
            text = rightText,
            modifier = Modifier
                .weight(40f)
                .border(1.dp, Color.Black)
                .padding(1.dp),
            textAlign = TextAlign.Center,
        )
    }
}

