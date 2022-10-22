package com.tipifreequiz.bavari.data.repository

import com.tipifreequiz.bavari.R
import com.tipifreequiz.bavari.data.datasource.AppDatabase
import com.tipifreequiz.bavari.data.model.Question
import com.tipifreequiz.bavari.data.model.QuizTopic
import com.tipifreequiz.bavari.data.model.toQuestion
import com.tipifreequiz.bavari.utils.Converters.toTopicString

class QuestionRepository(
    appDatabase: AppDatabase
) {
    private val dao = appDatabase.questionDao()!!

    fun getQuestionCount(topic: QuizTopic): Int {
        val stringTopic = topic.toTopicString()
        println("TOPIC: $stringTopic")
        return 10/*dao.getQuestionCountOfTopic(stringTopic)*/
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

        return Question(
            questionText = "",
            answers = listOf(),
            correctAnswerIndex = 0,
            image = images[position]
        ) /*questions[position]*/
    }
}