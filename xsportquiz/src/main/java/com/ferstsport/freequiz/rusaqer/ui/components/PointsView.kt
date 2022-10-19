package com.ferstsport.freequiz.rusaqer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ferstsport.freequiz.rusaqer.R
import com.ferstsport.freequiz.rusaqer.ui.theme.poppinsFontFamily

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
            fontFamily = poppinsFontFamily,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
        Text(
            text = points.toString(),
            fontFamily = poppinsFontFamily,
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