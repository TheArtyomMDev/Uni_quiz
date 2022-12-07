package com.avijekrl.proald.data.repository

import com.avijekrl.proald.data.datasource.AppDatabase
import com.avijekrl.proald.data.model.Question
import com.avijekrl.proald.data.model.QuizTopic
import com.avijekrl.proald.data.model.toQuestion
import com.avijekrl.proald.utils.Converters.toTopicString

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