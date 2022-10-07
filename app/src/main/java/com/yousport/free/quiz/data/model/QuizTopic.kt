package com.yousport.free.quiz.data.model

sealed class QuizTopic {
    object Players: QuizTopic()
    object Teams: QuizTopic()
    object Mix: QuizTopic()
    object Rules: QuizTopic()
}
