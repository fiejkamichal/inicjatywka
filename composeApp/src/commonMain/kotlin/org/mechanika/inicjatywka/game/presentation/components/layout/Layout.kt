package org.mechanika.inicjatywka.game.presentation.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Layout(
    floatingActionButton: (@Composable () -> Unit)? = null,
    topContent: (@Composable () -> Unit)? = null,
    middleContent: (@Composable () -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null,
    bottomSheet: (@Composable () -> Unit)? = null
) {
    Scaffold(
        floatingActionButton = {floatingActionButton?.let { it() }}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color( 0xFF94DF1A))
                .border(10.dp, Color( 0xFF324F0F))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                topContent?.let { it() }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                middleContent?.let{ it() }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                bottomContent?.let { it() }
            }
        }
    }
    bottomSheet?.let { it() }
}