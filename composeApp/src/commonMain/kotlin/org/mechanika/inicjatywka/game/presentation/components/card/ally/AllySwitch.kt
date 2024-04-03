package org.mechanika.inicjatywka.game.presentation.components.card.ally

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun AllySwitch(
    modifier: Modifier = Modifier,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {

    var rotated by mutableStateOf(value)

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    Image(
        modifier = modifier
            .size(75.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
                onValueChanged(rotated)
            },
        painter = painterResource(
            if (rotated)
                org.mechanika.inicjatywka.MR.images.allyiconlarge
            else
                org.mechanika.inicjatywka.MR.images.enemyiconlarge
        ),
        contentDescription = null
    )
}