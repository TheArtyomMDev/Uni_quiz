package com.dominikwannemaker.sportquiz.freo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dominikwannemaker.sportquiz.freo.utils.Converters
import com.dominikwannemaker.sportquiz.freo.utils.Converters.toStringList

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
