package com.quizking.draft.sport.ui.components

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.quizking.draft.sport.R
import com.quizking.draft.sport.data.model.QuizTopic


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
                normal = painterResource(id = R.drawable.normal_button_continue),
                pressed = painterResource(R.drawable.pressed_button_continue),
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                onClick(QuizTopic.Main)
            }

            Spacer(modifier = Modifier.height(50.dp))

            ImageButton(
                normal = painterResource(id = R.drawable.normal_button_new),
                pressed = painterResource(R.drawable.pressed_button_new),
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxSize()
            ) {
                onClick(QuizTopic.Main)
            }
        }

        Box {
            Image(
                painter = painterResource(R.drawable.bottom_bar),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height(75.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )
            BottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            )
        }
        
    }
}

@Composable
fun BottomBar(modifier: Modifier) {
    Row(
        modifier = modifier
    ) {
        val normalButtons = listOf(
            R.drawable.normal_button_menu,
            R.drawable.normal_button_setting,
            R.drawable.normal_button_share,
            R.drawable.normal_button_hint
        )

        val pressedButtons = listOf(
            R.drawable.pressed_button_menu,
            R.drawable.pressed_button_setting,
            R.drawable.pressed_button_share,
            R.drawable.pressed_button_hint
        )

        var clicked by remember {
            mutableStateOf(0)
        }

        for (i in 0..3) {

            val pic =
                if(clicked == i) pressedButtons[i]
                else normalButtons[i]

            Image(
                painter = painterResource(pic),
                contentDescription = null,
                modifier = Modifier
                    .weight(1F)
                    .clickable {
                        clicked = i
                    }
            )
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