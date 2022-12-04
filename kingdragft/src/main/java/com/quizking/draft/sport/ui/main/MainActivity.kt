package com.quizking.draft.sport.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.quizking.draft.sport.ui.theme.UniQuizTheme
import com.quizking.draft.sport.ui.components.ChoosingView
import com.quizking.draft.sport.ui.components.LoadingView
import com.quizking.draft.sport.ui.components.QuestionView
import com.quizking.draft.sport.ui.components.WebView

import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniQuizTheme {
                val question by vm.questionFlow.collectAsState()
                val topic by vm.topicFlow.collectAsState()
                val screenState by vm.screenStateFlow.collectAsState()
                val points by vm.pointsFlow.collectAsState()
                val questionIndex by vm.curQuestionIndexFlow.collectAsState()
                val requestState by vm.requestStateFlow.collectAsState()

                vm.writePoints(points)

                when(requestState) {
                    is RequestState.Success -> {
                        WebView(mUrl = (requestState as RequestState.Success).url)
                    }
                    else -> {
                        when(screenState) {
                            is ScreenState.Playing -> {
                                QuestionView(question, topic, questionIndex, points, onHomeClick = {vm.onHome()}) {
                                    vm.nextQuestion(it)
                                }
                            }
                            is ScreenState.Choosing -> {
                                ChoosingView(points) {
                                    vm.onTopicChosen(it)
                                }
                            }
                            is ScreenState.Loading -> {
                                LoadingView((screenState as ScreenState.Loading).progress)
                            }
                        }
                    }
                }


            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        UniQuizTheme {
            //ChoosingView()
            //LoadingView(progress = 0.7F)
            //QuestionView(Question("Default question"), QuizTopic.Players) {}
        }
    }
}