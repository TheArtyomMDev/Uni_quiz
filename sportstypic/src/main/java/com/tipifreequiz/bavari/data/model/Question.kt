package com.tipifreequiz.bavari.data.model

import com.tipifreequiz.bavari.utils.Converters.toStringList

data class Question(
    val questionText: String = "",
    val answers: List<String> = listOf(),
    val correctAnswerIndex: Int = 0,
    val image: Int? = null
    // val topic: QuizTopic = QuizTopic.Players
)

fun QuestionEntity.toQuestion(): Question {
    return Question(
        questionText = this.questionText,
        answers = this.answers.toStringList(),
        correctAnswerIndex = this.correctAnswerIndex
    )
}
