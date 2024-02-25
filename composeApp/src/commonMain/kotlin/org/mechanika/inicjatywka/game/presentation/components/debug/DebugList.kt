package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> DebugList(
    list: List<T>,
    name: String
) {
    Column(
        modifier = Modifier.fillMaxSize().border(1.dp, Color.Black),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Row {
            Spacer(Modifier.width(8.dp))
            Text(
                text = "$name (${list.size})",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)

            )
        }
        list.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "-: ${it.toString()}",
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}