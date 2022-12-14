package com.turnamentfoto.free.quiz.ui.main

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.turnamentfoto.free.quiz.data.datasource.APIService
import com.turnamentfoto.free.quiz.data.model.Question
import com.turnamentfoto.free.quiz.data.model.QuizTopic
import com.turnamentfoto.free.quiz.data.repository.QuestionRepository
import com.turnamentfoto.free.quiz.utils.Constants
import io.branch.referral.util.BRANCH_STANDARD_EVENT
import io.branch.referral.util.BranchEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await

class MainViewModel(
    private val questionRepo: QuestionRepository,
    private val dataStore: DataStore<Preferences>,
    private val apiService: APIService,
    private val context: Context
): ViewModel() {
    private var _curQuestionIndexFlow: MutableStateFlow<Int> = MutableStateFlow(0)

    private val _questionFlow: MutableStateFlow<Question> = MutableStateFlow(Question())
    private val _topicFlow: MutableStateFlow<QuizTopic> = MutableStateFlow(QuizTopic.Photo)
    private val _screenStateFlow: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Choosing)
    private val _pointsFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _requestStateFlow: MutableStateFlow<RequestState> = MutableStateFlow(RequestState.Loading)

    val questionFlow: StateFlow<Question> = _questionFlow
    val topicFlow: StateFlow<QuizTopic> = _topicFlow
    val screenStateFlow: StateFlow<ScreenState> = _screenStateFlow
    val pointsFlow: StateFlow<Int> = _pointsFlow
    val curQuestionIndexFlow: StateFlow<Int> = _curQuestionIndexFlow
    val requestStateFlow: StateFlow<RequestState> = _requestStateFlow

    init {
        updateConnectionStatus()
        viewModelScope.launch {
            dataStore.data.collectLatest {
                val points = it[Constants.POINTS]
                println("GETTING POINTS $points")
                if (points != null) {
                    _pointsFlow.emit(points)
                }
            }
        }
        viewModelScope.launch {
            _screenStateFlow.emit(ScreenState.Loading(0.1F))
            while(requestStateFlow.value == RequestState.Loading)
                for(progress in 1..10) {
                    _screenStateFlow.emit(ScreenState.Loading(progress/10F))
                    delay(100)
                }
            _screenStateFlow.emit(ScreenState.Choosing)
        }
        sendEvents()
    }

    private fun updateConnectionStatus() {
        CoroutineScope(Dispatchers.IO).launch {
            val urlFromDatabase = getUrlFromDatabase()
            if (urlFromDatabase.isBlank()) {
                _requestStateFlow.emit(RequestState.Failed)
            } else {
                dataStore.data.collectLatest { prefs ->
                    var urlWithParams = "$urlFromDatabase?"
                    urlWithParams += "advertising_id=" + prefs[Constants.ADVERTISING_ID] + "&"
                    urlWithParams += "appsflyer_id=" + prefs[Constants.APPSFLYER_ID] + "&"
                    urlWithParams += "campaign_id=" + ((prefs[Constants.CAMPAIGN_ID]) ?: "") + "&"
                    urlWithParams += "campaign_name=" + ((prefs[Constants.CAMPAIGN_NAME]) ?: "") + "&"
                    urlWithParams += "af_channel=" + ((prefs[Constants.AF_CHANNEL]) ?: "")
                    println("urlWithParams : $urlWithParams")
                    _requestStateFlow.emit(RequestState.Success(urlWithParams))
                }
            }
        }
        /*
        CoroutineScope(Dispatchers.IO).launch {
            println("updating connection status 1")

            dataStore.data.collectLatest { prefs ->
                println("updating connection status 2")
                try {
                    val response = apiService.testConnection(
                        advertisingId = prefs[Constants.ADVERTISING_ID],
                        appsflyerId = prefs[Constants.APPSFLYER_ID],
                        campaignId = prefs[Constants.CAMPAIGN_ID] ?: "",
                        campaignName = prefs[Constants.CAMPAIGN_NAME] ?: "",
                        afChannel = prefs[Constants.AF_CHANNEL] ?: ""
                    )
                    println("response made ${response.raw().isRedirect}, ${response.raw().isSuccessful}")
                    //if (response.raw().isRedirect)
                    //    _requestStateFlow.emit(RequestState.Success(getUrlFromDatabase()))
                    //else
                    //    _requestStateFlow.emit(RequestState.Failed)
                } catch (e: Exception) {
                    //_requestStateFlow.emit(RequestState.Failed)
                }

            }
        }
        */
    }

    fun writePoints(points: Int) {
        viewModelScope.launch {
            dataStore.edit { settings ->
                if (points == 0) {
                    val pointsDataStore = settings[Constants.POINTS]
                    if(pointsDataStore == null)
                        settings[Constants.POINTS] = 0
                } else {
                    println("Writing points $points")
                    settings[Constants.POINTS] = points
                }

            }
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

    private fun sendEvents() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.data.collectLatest { prefs ->
                println("AF CHANNEL is ${prefs[Constants.AF_CHANNEL]}")
                when(prefs[Constants.AF_CHANNEL]) {
                    "ACI_Search" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.ACHIEVE_LEVEL)
                            .setDescription("ACI_Search")
                            .logEvent(context)
                        /*
                        Event
                            .buildWithEventType(EventType.ACHIEVEMENT)
                            .setName("ACI_Search")
                            .send()

                         */
                    }
                    "ACI_Youtube" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.SHARE)
                            .setDescription("ACI_Youtube")
                            .logEvent(context)
                        /*
                        Event
                            .buildWithEventType(EventType.DEEPLINK)
                            .setName("ACI_Youtube")
                            .send()

                         */
                    }
                    "ACI_Display" -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.RATE)
                            .setDescription("ACI_Search")
                            .logEvent(context)
                        /*
                        Event
                            .buildWithEventType(EventType.RATING)
                            .setName("ACI_Search")
                            .send()

                         */
                    }
                    else -> {
                        BranchEvent(BRANCH_STANDARD_EVENT.VIEW_AD)
                            .setDescription("NoChannel")
                            .logEvent(context)
                        /*
                        Event
                            .buildWithEventType(EventType.VIEW)
                            .setName("NoChannel")
                            .send()

                         */
                        println("Event sent")
                    }
                }
            }
        }
    }

    private suspend fun getUrlFromDatabase(): String {
        return try {
            println("Started fetching")
            val database = Firebase.database
            val myRef = database.getReference("url")

            val fetchedUrl = myRef.get().await().value.toString()
            println("fetchedUrl : $fetchedUrl")
            return fetchedUrl
        } catch (e: Exception) {
            e.printStackTrace()
            ""
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