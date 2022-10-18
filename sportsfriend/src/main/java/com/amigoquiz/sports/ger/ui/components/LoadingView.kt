package com.amigoquiz.sports.ger.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amigoquiz.sports.ger.R

@Composable
fun LoadingView(progress: Float) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage()
        Box {
            /*
            Image(
                painter = painterResource(R.drawable.game_banner),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
            */

            ProgressBar(
                emptyImage = painterResource(R.drawable.empty_loading_bar),
                fullImage = painterResource(R.drawable.full_loading_bar),
                progress = progress,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 76.dp)
                    .padding(bottom = 76.dp)
                    .fillMaxWidth()
                    .height(30.dp)
            )
            /*
            Image(
                painter = painterResource(R.drawable.full_loading_bar),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 76.dp)
                    .padding(bottom = 76.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

             */
        }
    }
}