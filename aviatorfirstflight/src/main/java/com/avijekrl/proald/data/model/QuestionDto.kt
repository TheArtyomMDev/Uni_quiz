package com.avijekrl.proald.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionDto(
    @PrimaryKey
    var id: String = "default_id",

    val questionText: String = "",
    val answers: String = "",
    val correctAnswerIndex: Int = 0,
    val topic: String = ""
)
