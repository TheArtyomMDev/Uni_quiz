package com.dominikwannemaker.sportquiz.freo.data.model

sealed class QuizTopic {
    object Soccer: QuizTopic()
    object Boxing: QuizTopic()
    object Cricket: QuizTopic()
}
