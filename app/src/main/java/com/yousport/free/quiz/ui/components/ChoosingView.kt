package com.yousport.free.quiz.ui.components

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yousport.free.quiz.R
import com.yousport.free.quiz.data.model.Question
import com.yousport.free.quiz.data.model.QuizTopic


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChoosingView(points: Int, onClick: (topic: QuizTopic) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage()
        Box(modifier = Modifier.padding(24.dp)) {
            PointsView(points = points, modifier = Modifier.align(Alignment.TopEnd))
        }
        Box(modifier = Modifier.padding(24.dp)) {
            Image(
                painter = painterResource(R.drawable.game_banner),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            )
        }

        val pictures = mapOf(
            painterResource(id = R.drawable.section_players) to QuizTopic.Players,
            painterResource(id = R.drawable.section_teams) to QuizTopic.Teams,
            painterResource(id = R.drawable.section_mix) to QuizTopic.Mix,
            painterResource(id = R.drawable.section_rules) to QuizTopic.Rules
        )
        //val interactionSource = remember { MutableInteractionSource() }
        //val isPressed by interactionSource.collectIsPressedAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyVerticalGrid(
                cells  = GridCells.Fixed(2),
               // contentPadding = PaddingValues(40.dp)
            ) {
                items(pictures.keys.toList()) { item ->
                    TopicItem(image = item) {
                        onClick(pictures[item]!!)
                    }
                }
            }

            val activity = LocalContext.current as Activity
            ImageButton(
                normal = painterResource(id = R.drawable.exit_button),
                pressed = painterResource(R.drawable.pressed_exit_button),
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxSize()
            ) {
                activity.finish()
            }
        }
        
    }
}

@Composable
fun TopicItem(image: Painter, onClick: () -> Unit) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .height(300.dp)
            .padding(10.dp)
            .fillMaxSize()
            .clickable {
                onClick()
            }
    )
}