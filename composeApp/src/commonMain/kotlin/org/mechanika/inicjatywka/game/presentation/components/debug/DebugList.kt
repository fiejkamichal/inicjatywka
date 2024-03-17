package org.mechanika.inicjatywka.game.presentation.components.debug

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> DebugList(
    list: List<T>,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    ) {
        Text("$name (${list.size})")
        list.forEach {
            Text("-: ${it.toString()}")
        }
    }
}