package com.yousport.free.quiz.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousport.free.quiz.data.model.Question
import com.yousport.free.quiz.data.model.QuizTopic
import com.yousport.free.quiz.data.repository.QuestionRepository
import com.yousport.free.quiz.utils.Converters
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val questionRepo: QuestionRepository
): ViewModel() {
    private var _curQuestionIndexFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _questionFlow: MutableStateFlow<Question> = MutableStateFlow(Question())
    private val _topicFlow: MutableStateFlow<QuizTopic> = MutableStateFlow(QuizTopic.Players)
    private val _screenStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Choosing)
    private val _pointsFlow: MutableStateFlow<Int> = MutableStateFlow(0) // DataStore

    val questionFlow: StateFlow<Question> = _questionFlow
    val topicFlow: StateFlow<QuizTopic> = _topicFlow
    val screenStateFlow: MutableStateFlow<ScreenState> = _screenStateFlow
    val pointsFlow: StateFlow<Int> = _pointsFlow
    val curQuestionIndexFlow: StateFlow<Int> = _curQuestionIndexFlow

    init {
        /*
        println("CONVERTING")
        val answers = listOf("Футболист", "Таджик", "Привет")
        println("CONVERTED: ${Converters.fromList(answers)}")
         */

        viewModelScope.launch {
            for(progress in 1..10) {
                _screenStateFlow.emit(ScreenState.Loading(progress/10F))
                delay(100)
            }
            _screenStateFlow.emit(ScreenState.Choosing)
        }
    }

    fun onHome() {
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Choosing)
            _curQuestionIndexFlow.emit(0)
        }
    }
    
    fun onTopicChosen(topic: QuizTopic) {
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Playing)
            _topicFlow.emit(topic)
            nextQuestion(false)
        }
    }

    fun nextQuestion(isLatestCorrect: Boolean) {
        viewModelScope.launch {
            if(isLatestCorrect) _pointsFlow.emit(_pointsFlow.value + 10)

            val curQuestionIndex = _curQuestionIndexFlow.value
            if (curQuestionIndex < questionRepo.getQuestionCount(topicFlow.value)) {
                _questionFlow.emit(
                    questionRepo.getQuestion(topicFlow.value, curQuestionIndex)
                )
                _curQuestionIndexFlow.emit(_curQuestionIndexFlow.value + 1)
            }
            else {
                _curQuestionIndexFlow.emit(0)
                _screenStateFlow.emit(ScreenState.Choosing)
            }
        }
    }
}