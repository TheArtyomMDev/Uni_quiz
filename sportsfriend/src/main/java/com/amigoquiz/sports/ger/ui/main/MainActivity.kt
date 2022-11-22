package com.amigoquiz.sports.ger.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.amigoquiz.sports.ger.data.model.Question
import com.amigoquiz.sports.ger.data.model.QuizTopic
import com.amigoquiz.sports.ger.ui.components.ChoosingView
import com.amigoquiz.sports.ger.ui.components.LoadingView
import com.amigoquiz.sports.ger.ui.components.QuestionView
import com.amigoquiz.sports.ger.ui.components.WebView
import com.amigoquiz.sports.ger.ui.theme.UniQuizTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.prefs.Preferences

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