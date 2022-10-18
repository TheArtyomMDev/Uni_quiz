package com.amigoquiz.sports.ger.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amigoquiz.sports.ger.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageButton(
    normal: Painter,
    pressed: Painter,
    modifier: Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Image(
        painter = if (!isPressed) normal else pressed,
        contentDescription = null,
        modifier = modifier
            .combinedClickable(
                interactionSource = interactionSource,
                null,
                onClick = {
                    onClick()
                }
            )
    )
}