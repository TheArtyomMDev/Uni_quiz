package com.ferstsport.freequiz.rusaqer.ui.main

sealed class ScreenState {
    object Playing: ScreenState()
    object Choosing: ScreenState()
    data class Loading(val progress: Float): ScreenState()
}
