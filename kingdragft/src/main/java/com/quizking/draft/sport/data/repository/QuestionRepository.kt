package com.quizking.draft.sport.data.repository

import com.quizking.draft.sport.data.datasource.AppDatabase
import com.quizking.draft.sport.data.model.Question
import com.quizking.draft.sport.data.model.QuizTopic
import com.quizking.draft.sport.data.model.toQuestion
import com.quizking.draft.sport.utils.Converters.toTopicString

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