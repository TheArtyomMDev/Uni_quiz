package com.amigoquiz.sports.ger.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.amigoquiz.sports.ger.R
import com.amigoquiz.sports.ger.ui.theme.dincondensedFontFamily

@Composable
fun PointsView(
    points: Int,
    modifier: Modifier
) {
    
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SCORE",
            fontFamily = dincondensedFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        )
        Text(
            text = points.toString(),
            fontFamily = dincondensedFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = Color.Yellow
        )
        /*
        Image(
            painter = painterResource(R.drawable.icon_score),
            contentDescription = null,
            modifier = Modifier
                .height(15.dp)
                .width(20.dp)
                .fillMaxSize()
        )

         */
    }
}