package com.amigoquiz.sports.ger.data.datasource

import androidx.room.*
import com.amigoquiz.sports.ger.data.model.Question
import com.amigoquiz.sports.ger.data.model.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE topic = :topic")
    fun getQuestionsFromTopic(topic: String): List<QuestionEntity>

    @Query("SELECT COUNT(*) FROM questions WHERE topic = :topic")
    fun getQuestionCountOfTopic(topic: String): Int

   // @Query("SELECT * FROM hrvparams WHERE name = :name")
   // fun getByName(name: String): Question?

    /*
    @Insert
    fun insert(hrvParam: HRVParam)

    @Update
    fun update(hrvParam: HRVParam)

    @Delete
    fun delete(hrvParam: Question)

     */
}