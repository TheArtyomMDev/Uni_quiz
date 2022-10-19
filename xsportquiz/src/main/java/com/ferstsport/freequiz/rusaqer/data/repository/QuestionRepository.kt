package com.ferstsport.freequiz.rusaqer.data.repository

import com.ferstsport.freequiz.rusaqer.data.datasource.AppDatabase
import com.ferstsport.freequiz.rusaqer.data.model.Question
import com.ferstsport.freequiz.rusaqer.data.model.QuizTopic
import com.ferstsport.freequiz.rusaqer.data.model.toQuestion
import com.ferstsport.freequiz.rusaqer.utils.Converters.toString
import com.ferstsport.freequiz.rusaqer.utils.Converters.toTopicString

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