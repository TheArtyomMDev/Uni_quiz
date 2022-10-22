package com.tipifreequiz.bavari.ui.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tipifreequiz.bavari.R
import com.tipifreequiz.bavari.data.model.Question
import com.tipifreequiz.bavari.data.model.QuizTopic
import com.tipifreequiz.bavari.ui.theme.interSemiBoldFontFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuestionView(
    question: Question,
    topic: QuizTopic,
    questionIndex: Int,
    points: Int,
    onHomeClick: () -> Unit,
    doOnClick: (isCorrect: Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BackgroundImage()
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                TopicHeader(topic = topic) {
                    onHomeClick()
                }
                PointsView(
                    points = points,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp)
                )
            }

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                QuestionText(question, questionIndex)
                LazyVerticalGrid(
                    cells  = GridCells.Fixed(2),
                    // contentPadding = PaddingValues(40.dp)
                ) {
                    items(listOf(0, 1)) { i ->
                        AnswerButton(
                            i,
                            isCorrect = i == question.correctAnswerIndex,
                            doOnClick = doOnClick
                        )
                    }
                }
            }
        }


        val context = LocalContext.current
        Box() {
            Image(
                painter = painterResource(R.drawable.bottom_bar),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
            Row(
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {


                ImageButton(
                    normal = painterResource(R.drawable.pressed_quiz_button),
                    pressed = painterResource(R.drawable.pressed_quiz_button),
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .height(40.dp)
                        .width(40.dp)
                        .align(Alignment.Bottom)
                        //.fillMaxSize()
                ) {}

                //Spacer(modifier = Modifier.width(200.dp))

                ImageButton(
                    normal = painterResource(R.drawable.menu_button),
                    pressed = painterResource(R.drawable.pressed_menu_button),
                    modifier = Modifier
                        //.padding(bottom = 24.dp)
                        .height(150.dp)
                        .width(150.dp)
                        .fillMaxSize()
                ) {
                    onHomeClick()
                }

                ImageButton(
                    normal = painterResource(R.drawable.setting_button),
                    pressed = painterResource(R.drawable.setting_button),
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .height(40.dp)
                        .width(40.dp)
                        .align(Alignment.Bottom)
                        //.fillMaxSize()
                ) {}


            }
        }
    }
}

@Composable
fun TopicHeader(topic: QuizTopic, onHomeClick: () -> Unit) {

    Box {
        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }

}

@Composable
fun QuestionText(question: Question, questionIndex: Int) {
    Box() {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(350.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds
        )

        Image(
                painter = painterResource(question.image!!), /*painterResource(R.drawable.q1)*/
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 96.dp)
                    //.height(300.dp)
                    .matchParentSize()
                    //.wrapContentHeight()
                    .align(Alignment.BottomCenter),
            )

        /*
        Text(
            color = Color.Black,
            text = question.questionText,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .padding(24.dp)
                .matchParentSize()
                .wrapContentHeight()
                .align(Alignment.Center),
            fontFamily = interSemiBoldFontFamily
            )
        Text(
            color = Color.Black,
            text = questionIndex.toString(),
           // textAlign = TextAlign.Top,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .padding(30.dp)
                //.matchParentSize()
                //.wrapContentHeight()
                .align(Alignment.TopCenter),
            fontFamily = interSemiBoldFontFamily
        )

         */

        /*
        val context = LocalContext.current
        ImageButton(
            normal = painterResource(R.drawable.hints_button),
            pressed = painterResource(R.drawable.pressed_hints_button),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .height(40.dp)
                .fillMaxSize()
                .align(Alignment.BottomCenter),
        ) {
            Toast.makeText(context, question.answers[question.correctAnswerIndex], Toast.LENGTH_SHORT).show()
        }

         */
    }
}

@Composable
fun AnswerButton(index: Int, isCorrect: Boolean = false,
                 doOnClick: (isCorrect: Boolean) -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                onClick = {
                    doOnClick(isCorrect)
                }
            )
    ) {
        Image(
            painter = painterResource(if(index == 0) R.drawable.true_button else R.drawable.false_button),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .height(70.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds,
        )
        /*
        Text(
            color = Color.White,
            text = text,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            modifier = Modifier
                .matchParentSize()
                .wrapContentHeight()
                .align(Alignment.Center),
            fontFamily = interSemiBoldFontFamily
        )

         */
    }
}