package com.amigoquiz.sports.ger.data.model

sealed class QuizTopic {
    object Basketball: QuizTopic()
    object Tennis: QuizTopic()
    object Hockey: QuizTopic()
    object Mixed: QuizTopic()
}
