package com.quizking.draft.sport.ui.components

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
import com.quizking.draft.sport.R
import com.quizking.draft.sport.data.model.Question
import com.quizking.draft.sport.data.model.QuizTopic


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
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(10.dp))
                PointsView(
                    points = points,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 24.dp)
                )
                Spacer(Modifier.height(10.dp))

                TopicHeader(topic = topic, questionIndex = questionIndex) {
                    onHomeClick()
                }
            }

            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                QuestionText(question, questionIndex)
                LazyVerticalGrid(
                    cells  = GridCells.Fixed(1),
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
fun TopicHeader(topic: QuizTopic,  questionIndex: Int, onHomeClick: () -> Unit) {

    Box() {
        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            "$questionIndex",
            Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
    }

}

@Composable
fun QuestionText(question: Question, questionIndex: Int) {
    Box(Modifier.height(200.dp).width(400.dp)) {

        Text(
            color = Color.White,
            text = question.questionText,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .padding(24.dp)
                .matchParentSize()
                .wrapContentHeight()
                .align(Alignment.Center)
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
                .height(60.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            contentScale = ContentScale.FillBounds,
        )
        Text(
            color = Color.Black,
            text = text,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier
                .matchParentSize()
                .wrapContentHeight()
                .align(Alignment.Center)
        )
    }
}