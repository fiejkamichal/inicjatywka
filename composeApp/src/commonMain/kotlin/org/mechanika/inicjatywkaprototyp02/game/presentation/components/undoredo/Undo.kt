package org.mechanika.inicjatywkaprototyp02.game.presentation.components.undoredo

import androidx.compose.material.Button
import androidx.compose.runtime.Composable

@Composable
fun Undo(
    onClick: ()-> Unit
) {
    Button (
        onClick = { onClick() }
    ) {

    }
}