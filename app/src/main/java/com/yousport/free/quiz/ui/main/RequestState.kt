package com.yousport.free.quiz.ui.main

sealed class RequestState {
    object Loading: RequestState()
    object Success: RequestState()
    object Failed: RequestState()
}
