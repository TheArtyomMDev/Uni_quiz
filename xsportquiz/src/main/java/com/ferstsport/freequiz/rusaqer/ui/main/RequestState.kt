package com.ferstsport.freequiz.rusaqer.ui.main

sealed class RequestState {
    object Loading: RequestState()
    object Success: RequestState()
    object Failed: RequestState()
}
