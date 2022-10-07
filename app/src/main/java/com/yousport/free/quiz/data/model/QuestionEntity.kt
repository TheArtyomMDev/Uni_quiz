package com.yousport.free.quiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey
    var id: String = "default_id",

    val questionText: String = "",
    val answers: String = "",
    val correctAnswerIndex: Int = 0,
    val topic: String = ""
)
