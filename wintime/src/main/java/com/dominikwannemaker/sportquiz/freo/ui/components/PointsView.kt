package com.dominikwannemaker.sportquiz.freo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dominikwannemaker.sportquiz.freo.ui.theme.compactFontFamily


@Composable
fun PointsView(
    points: Int,
    modifier: Modifier
) {
    
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "SCORE: ",
            fontFamily = compactFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        Text(
            text = points.toString(),
            fontFamily = compactFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
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