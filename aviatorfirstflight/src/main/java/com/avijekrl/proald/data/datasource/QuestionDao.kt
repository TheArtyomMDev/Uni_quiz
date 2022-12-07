package com.avijekrl.proald.data.datasource

import androidx.room.*
import com.avijekrl.proald.data.model.QuestionDto

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE topic = :topic")
    fun getQuestionsFromTopic(topic: String): List<QuestionDto>

    @Query("SELECT COUNT(*) FROM questions WHERE topic = :topic")
    fun getQuestionCountOfTopic(topic: String): Int
}