package com.dominikwannemaker.sportquiz.freo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.dominikwannemaker.sportquiz.freo.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}