package com.avijekrl.proald.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.avijekrl.proald.R
import com.avijekrl.proald.data.model.Question
import com.avijekrl.proald.data.model.QuizTopic
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


data class Coordinates(var x: Dp, var y: Dp)

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
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged {
                size = it
            }
        ,
        color = MaterialTheme.colorScheme.background,
    ) {
        BackgroundImage()

        var playerY by remember {
            mutableStateOf(200.dp /*Coordinates(10.dp, 400.dp)*/)
        }

        var buildingsX by remember {
            mutableStateOf(200.dp)
        }

        var seconds by remember {
            mutableStateOf(0)
        }

        Box(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    playerY -= 25.dp
                }
            )
        }) {}

        Box {
            Column(Modifier.align(Alignment.TopEnd), horizontalAlignment = Alignment.End) {
                Box {
                    Image(
                        painter = painterResource(R.drawable.timer_frame),
                        contentDescription = null,
                        //modifier = Modifier.padding(end = 10.dp)
                    )
                    Text(" ${seconds / 60}:${seconds % 60}", modifier = Modifier.align(Alignment.Center))
                }
                Spacer(Modifier.height(10.dp))
                Box {
                    Image(
                        painter = painterResource(R.drawable.score_frame),
                        contentDescription = null,
                        //modifier = Modifier.offset(x = (-10).dp)
                    )
                    Text((seconds / 2).toString(), modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        val resources = LocalContext.current.resources
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                while(true) {
                    seconds += 1
                    delay(1000)
                }
            }
            scope.launch {
                while(true) {
                    playerY += 10.dp
                    buildingsX -= 10.dp

                    if (buildingsX < 0.dp) buildingsX = 200.dp

                    if(playerY < 0.dp) playerY = 0.dp
                    if(playerY > 500.dp) playerY = (490.dp)

                    delay(100)
                }
            }
        }

        Box {
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .offset(10.dp, playerY),
                painter = painterResource(R.drawable.player),
                contentDescription = null
            )
            Row(Modifier
                .align(Alignment.BottomCenter)
                .offset(buildingsX, 0.dp)) {
                for(i in 1..4) {
                    Column {
                        Image(
                            modifier = Modifier
                                .width(100.dp)
                                .height(200.dp),
                            painter = painterResource(R.drawable.cloud),
                            contentDescription = null
                        )

                        val heightSpace by remember {
                            mutableStateOf((Random.nextInt(100, 150)).dp)
                        }
                        Spacer(Modifier.height(heightSpace))

                        Image(
                            modifier = Modifier
                                .width(100.dp)
                                .height(200.dp),
                            painter = painterResource(R.drawable.building),
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.width(100.dp))
                }
            }
        }



        /*
        Canvas(Modifier.fillMaxSize()) {

            with(LocalDensity) {
                var playerBitmap = BitmapFactory.decodeResource(resources, R.drawable.player)
                playerBitmap = Bitmap.createScaledBitmap(playerBitmap,
                    160.dp.toPx().toInt(), 120.dp.toPx().toInt(), false)
                drawImage(playerBitmap.asImageBitmap(), Offset(player.x.toPx(), player.y.toPx()))
            }


            }

         */


        /*
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

         */
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
    Box(Modifier
        .height(200.dp)
        .width(400.dp)) {

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