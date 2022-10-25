package com.turnamentfoto.free.quiz.data.repository

import com.turnamentfoto.free.quiz.R
import com.turnamentfoto.free.quiz.data.datasource.AppDatabase
import com.turnamentfoto.free.quiz.data.model.Question
import com.turnamentfoto.free.quiz.data.model.QuizTopic
import com.turnamentfoto.free.quiz.data.model.toQuestion
import com.turnamentfoto.free.quiz.utils.Converters.toString
import com.turnamentfoto.free.quiz.utils.Converters.toTopicString

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

        val images = listOf(
            R.drawable.q1,
            R.drawable.q2,
            R.drawable.q3,
            R.drawable.q4,
            R.drawable.q5,
            R.drawable.q6,
            R.drawable.q7,
            R.drawable.q8,
            R.drawable.q9,
            R.drawable.q10,
        )

        if(topic is QuizTopic.Photo) {
            questions[position].image = images[position]
        }

        return questions[position]
    }
}