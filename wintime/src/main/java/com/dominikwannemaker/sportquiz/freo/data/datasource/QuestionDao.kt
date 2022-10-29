package com.dominikwannemaker.sportquiz.freo.data.datasource

import androidx.room.*
import com.dominikwannemaker.sportquiz.freo.data.model.Question
import com.dominikwannemaker.sportquiz.freo.data.model.QuestionEntity

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