package com.turnamentfoto.free.quiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.turnamentfoto.free.quiz.utils.Converters
import com.turnamentfoto.free.quiz.utils.Converters.toStringList

data class Question(
    val questionText: String = "",
    val answers: List<String> = listOf(),
    val correctAnswerIndex: Int = 0,
    var image: Int? = null
    // val topic: QuizTopic = QuizTopic.Players
)

fun QuestionEntity.toQuestion(): Question {
    return Question(
        questionText = this.questionText,
        answers = this.answers.toStringList(),
        correctAnswerIndex = this.correctAnswerIndex
    )
}
