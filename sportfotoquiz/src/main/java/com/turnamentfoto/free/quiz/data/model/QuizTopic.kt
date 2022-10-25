package com.turnamentfoto.free.quiz.data.model

sealed class QuizTopic {
    object Photo: QuizTopic()
    object Leaugue: QuizTopic()
}
