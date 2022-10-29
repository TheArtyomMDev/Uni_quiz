package com.dominikwannemaker.sportquiz.freo.data.repository

import com.dominikwannemaker.sportquiz.freo.R
import com.dominikwannemaker.sportquiz.freo.data.datasource.AppDatabase
import com.dominikwannemaker.sportquiz.freo.data.model.Question
import com.dominikwannemaker.sportquiz.freo.data.model.QuizTopic
import com.dominikwannemaker.sportquiz.freo.data.model.toQuestion
import com.dominikwannemaker.sportquiz.freo.utils.Converters.toString
import com.dominikwannemaker.sportquiz.freo.utils.Converters.toTopicString

class QuestionRepository(
    appDatabase: AppDatabase
) {
    private val dao = appDatabase.questionDao()!!

    fun getQuestionCount(topic: QuizTopic): Int {
        val stringTopic = topic.toTopicString()
        println("TOPIC: $stringTopic")
        return dao.getQuestionCountOfTopic(stringTopic)
    }

    fun getQuestion(topic: QuizTopic, position: Int): Question {
        val questionEntities = dao.getQuestionsFromTopic(topic.toTopicString())
        val questions = questionEntities.map { it.toQuestion() }
        return questions[position]
    }
}