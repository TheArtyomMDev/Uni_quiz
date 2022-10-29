package com.dominikwannemaker.sportquiz.freo.ui.components

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
import com.dominikwannemaker.sportquiz.freo.R
import com.dominikwannemaker.sportquiz.freo.data.model.Question
import com.dominikwannemaker.sportquiz.freo.data.model.QuizTopic
import com.dominikwannemaker.sportquiz.freo.ui.theme.compactFontFamily


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
                /*
                PointsView(
                    points = points,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp)


                )

                 */
            }

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                QuestionText(question, questionIndex)
                for (i in question.answers.indices) {
                    Spacer(modifier = Modifier.height(12.dp))
                    AnswerButton(
                        question.answers[i],
                        isCorrect = i == question.correctAnswerIndex,
                        doOnClick = doOnClick
                    )
                }
            }
        }
    }
}

@Composable
fun TopicHeader(topic: QuizTopic, onHomeClick: () -> Unit) {
    val picture = when(topic) {
        is QuizTopic.Boxing -> painterResource(R.drawable.header_boxing)
        is QuizTopic.Cricket -> painterResource(R.drawable.header_cricket)
        is QuizTopic.Soccer -> painterResource(R.drawable.header_soccer)
    }
    Box {
        Image(
            painter = picture,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        ImageButton(
            normal = painterResource(R.drawable.menu_button),
            pressed = painterResource(R.drawable.pressed_menu_button),
            modifier = Modifier
                .padding(start = 24.dp, bottom = 24.dp)
                .height(40.dp)
                .width(40.dp)
                .fillMaxSize()
                .align(Alignment.BottomStart),
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
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds
        )

        if(question.image != null) {
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
        } else {
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
                fontFamily = compactFontFamily
            )
        }

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
            fontFamily = compactFontFamily
        )


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
            fontFamily = compactFontFamily
        )
    }
}