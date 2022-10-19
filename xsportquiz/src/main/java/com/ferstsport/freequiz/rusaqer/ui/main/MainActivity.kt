package com.ferstsport.freequiz.rusaqer.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.ferstsport.freequiz.rusaqer.data.model.Question
import com.ferstsport.freequiz.rusaqer.data.model.QuizTopic
import com.ferstsport.freequiz.rusaqer.ui.components.ChoosingView
import com.ferstsport.freequiz.rusaqer.ui.components.LoadingView
import com.ferstsport.freequiz.rusaqer.ui.components.QuestionView
import com.ferstsport.freequiz.rusaqer.ui.components.WebView
import com.ferstsport.freequiz.rusaqer.ui.theme.UniQuizTheme
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
                        WebView()
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