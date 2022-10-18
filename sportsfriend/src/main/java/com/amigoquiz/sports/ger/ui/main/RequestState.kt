package com.amigoquiz.sports.ger.ui.main

sealed class RequestState {
    object Loading: RequestState()
    object Success: RequestState()
    object Failed: RequestState()
}
