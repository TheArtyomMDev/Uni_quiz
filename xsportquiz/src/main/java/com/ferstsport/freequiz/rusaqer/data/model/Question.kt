package com.ferstsport.freequiz.rusaqer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ferstsport.freequiz.rusaqer.utils.Converters
import com.ferstsport.freequiz.rusaqer.utils.Converters.toStringList

data class Question(
    val questionText: String = "",
    val answers: List<String> = listOf(),
    val correctAnswerIndex: Int = 0,
    // val topic: QuizTopic = QuizTopic.Players
)

fun QuestionEntity.toQuestion(): Question {
    return Question(
        questionText = this.questionText,
        answers = this.answers.toStringList(),
        correctAnswerIndex = this.correctAnswerIndex
    )
}
