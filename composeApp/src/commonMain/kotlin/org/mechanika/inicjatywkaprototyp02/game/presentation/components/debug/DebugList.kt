package org.mechanika.inicjatywkaprototyp02.game.presentation.components.debug

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
import androidx.compose.ui.unit.dp

@Composable
fun <T> DebugList(
    list: List<T>,
    name: String
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        Row {
            Text(
                text = "$name (${list.size})",
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp)

            )
        }
        list.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "-: ${it.toString()}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}