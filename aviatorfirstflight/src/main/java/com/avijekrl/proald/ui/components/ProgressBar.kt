package com.avijekrl.proald.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ProgressBar(
    emptyImage: Painter,
    fullImage: Painter,
    progress: Float = 1.0F,
    modifier: Modifier
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(modifier = modifier.onSizeChanged {
        size = it
    }) {
        Image(
            painter = emptyImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = fullImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .width(
                    with(LocalDensity.current) {
                        (size.width.toDp() * progress)
                    }
                )
        )
    }
}