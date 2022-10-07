package com.yousport.free.quiz.data.repository

import com.yousport.free.quiz.data.datasource.AppDatabase
import com.yousport.free.quiz.data.model.Question
import com.yousport.free.quiz.data.model.QuizTopic
import com.yousport.free.quiz.data.model.toQuestion
import com.yousport.free.quiz.utils.Converters.toString
import com.yousport.free.quiz.utils.Converters.toTopicString

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