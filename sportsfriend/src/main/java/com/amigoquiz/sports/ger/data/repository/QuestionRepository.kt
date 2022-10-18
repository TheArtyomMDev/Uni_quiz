package com.amigoquiz.sports.ger.data.repository

import com.amigoquiz.sports.ger.data.datasource.AppDatabase
import com.amigoquiz.sports.ger.data.model.Question
import com.amigoquiz.sports.ger.data.model.QuizTopic
import com.amigoquiz.sports.ger.data.model.toQuestion
import com.amigoquiz.sports.ger.utils.Converters.toString
import com.amigoquiz.sports.ger.utils.Converters.toTopicString

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