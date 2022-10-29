package com.dominikwannemaker.sportquiz.freo.ui.components

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
import com.dominikwannemaker.sportquiz.freo.R
import com.dominikwannemaker.sportquiz.freo.data.model.QuizTopic


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
            Column {
                val topics = mapOf(
                    QuizTopic.Soccer to painterResource(R.drawable.soccer_button),
                    QuizTopic.Boxing to painterResource(R.drawable.boxing_button),
                    QuizTopic.Cricket to painterResource(R.drawable.cricket_button)
                )
                for (topic in topics.keys) {
                    ImageButton(
                        normal = topics[topic]!!,
                        pressed = topics[topic]!!,
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxSize()
                    ) {
                        onClick(topic)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

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