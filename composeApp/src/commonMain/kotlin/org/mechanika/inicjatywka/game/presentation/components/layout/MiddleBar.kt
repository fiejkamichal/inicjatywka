package org.mechanika.inicjatywka.game.presentation.components.layout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiddleBar(
    modifier: Modifier = Modifier,
    leftContent: @Composable () -> Unit = {},
    rightContent: @Composable () -> Unit = {},
    middleContent: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(40f)
                .fillMaxHeight()
                .border(1.dp, Color.Black)
                .padding(1.dp),
        ) {
            leftContent()
        }
        Box(
            modifier = Modifier
                .weight(20f)
                .fillMaxHeight()
                .border(1.dp, Color.Black)
                .padding(1.dp),
        ) {
            middleContent()
        }
        Box(
            modifier = Modifier
                .weight(40f)
                .fillMaxHeight()
                .border(1.dp, Color.Black)
                .padding(1.dp),
        ) {
            rightContent()
        }
    }
}