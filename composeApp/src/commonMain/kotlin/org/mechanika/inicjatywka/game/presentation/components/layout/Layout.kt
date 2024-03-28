package org.mechanika.inicjatywka.game.presentation.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Layout(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = { },
    topLeftText: String = "",
    topRightText: String = "",
    topMiddleContent: @Composable () -> Unit = {},
    middleLeftContent: @Composable () -> Unit = {},
    middleMiddleContent: @Composable () -> Unit = {},
    middleRightContent: @Composable () -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
    bottomSheet: @Composable () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = floatingActionButton
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFF974C27))
                .border(10.dp, Color(0xFF324F0F))
                .padding(10.dp)
        ) {
            TopBar(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth(),
                leftText = topLeftText,
                rightText = topRightText,
                middleContent = topMiddleContent
            )
            MiddleBar (
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth(),
                leftContent = middleLeftContent,
                middleContent = middleMiddleContent,
                rightContent = middleRightContent
            )
            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                bottomContent()
            }
        }
    }
    bottomSheet()
}