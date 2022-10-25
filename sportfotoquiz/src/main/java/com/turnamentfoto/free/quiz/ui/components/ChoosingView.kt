package com.turnamentfoto.free.quiz.ui.components

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turnamentfoto.free.quiz.R
import com.turnamentfoto.free.quiz.data.model.QuizTopic


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChoosingView(points: Int, onClick: (topic: QuizTopic) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage()


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageButton(
                normal = painterResource(id = R.drawable.photo_quiz_button),
                pressed = painterResource(R.drawable.pressed_photo_quiz_button),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxSize()
            ) {
                onClick(QuizTopic.Photo)
            }

            Spacer(modifier = Modifier.height(20.dp))

            ImageButton(
                normal = painterResource(id = R.drawable.leaugue_quizl_button),
                pressed = painterResource(R.drawable.pressed_leaugue_quiz_button),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxSize()
            ) {
                onClick(QuizTopic.Leaugue)
            }

            Spacer(modifier = Modifier.height(100.dp))

            val activity = LocalContext.current as Activity
            ImageButton(
                normal = painterResource(id = R.drawable.exit_button),
                pressed = painterResource(R.drawable.pressed_exit_button),
                modifier = Modifier
                    .height(60.dp)
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