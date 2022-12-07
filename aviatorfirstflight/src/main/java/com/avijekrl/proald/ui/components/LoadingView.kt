package com.avijekrl.proald.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.avijekrl.proald.R

@Composable
fun LoadingView(progress: Float) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage(painterResource(R.drawable.loading_background))
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {

                Spacer(Modifier.height(650.dp))

                Text("loading...")

                Spacer(Modifier.height(10.dp))

                ProgressBar(
                    emptyImage = painterResource(R.drawable.empty_loading_bar),
                    fullImage = painterResource(R.drawable.full_loading_bar),
                    progress = progress,
                    modifier = Modifier
                        .padding(horizontal = 76.dp)
                        .padding(bottom = 76.dp)
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }

        }
    }
}