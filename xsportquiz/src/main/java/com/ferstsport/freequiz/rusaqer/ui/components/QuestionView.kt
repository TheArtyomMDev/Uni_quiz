package com.ferstsport.freequiz.rusaqer.ui.components

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
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ferstsport.freequiz.rusaqer.R
import com.ferstsport.freequiz.rusaqer.data.model.Question
import com.ferstsport.freequiz.rusaqer.data.model.QuizTopic
import com.ferstsport.freequiz.rusaqer.ui.theme.poppinsFontFamily


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
                    items(question.answers.indices.toList()) { i ->
                        AnswerButton(
                            question.answers[i],
                            isCorrect = i == question.correctAnswerIndex,
                            doOnClick = doOnClick
                        )
                    }
                }
            }
        }

        val context = LocalContext.current
        Box() {
            Row(
                modifier = Modifier.align(Alignment.BottomCenter),
            ) {
                ImageButton(
                    normal = painterResource(R.drawable.hints_button),
                    pressed = painterResource(R.drawable.hints_button),
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                        .fillMaxSize()
                        //.align(Alignment.CenterVertically)
                ) {
                    Toast.makeText(context, question.answers[question.correctAnswerIndex], Toast.LENGTH_SHORT).show()
                }
                ImageButton(
                    normal = painterResource(R.drawable.skip_button),
                    pressed = painterResource(R.drawable.skip_button),
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                        .fillMaxSize()
                        //.align(Alignment.CenterVertically)
                ) {
                    doOnClick(false)
                }
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
        ImageButton(
            normal = painterResource(R.drawable.menu_button),
            pressed = painterResource(R.drawable.pressed_menu_button),
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .height(20.dp)
                .width(20.dp)
                .fillMaxSize()
                .align(Alignment.TopStart),
        ) {
            onHomeClick()
        }
    }

}

@Composable
fun QuestionText(question: Question, questionIndex: Int) {
    Box() {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds
        )
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
            fontFamily = poppinsFontFamily
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
            fontFamily = poppinsFontFamily
        )

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
fun AnswerButton(text: String, isCorrect: Boolean = false,
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
            painter = painterResource(R.drawable.answer_button),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds,
        )
        Text(
            color = Color.White,
            text = text,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            modifier = Modifier
                .matchParentSize()
                .wrapContentHeight()
                .align(Alignment.Center),
            fontFamily = poppinsFontFamily
        )
    }
}