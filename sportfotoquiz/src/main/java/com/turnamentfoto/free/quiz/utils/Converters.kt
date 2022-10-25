package com.turnamentfoto.free.quiz.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.turnamentfoto.free.quiz.data.model.QuizTopic
import java.lang.reflect.Type

object Converters {
    fun List<String>.toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    fun String.toStringList(): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(this, listType)
    }

    fun String.toQuizTopic(): QuizTopic {
        val type: Type = object : TypeToken<QuizTopic>() {}.type
        return Gson().fromJson(this, type)
    }

    fun QuizTopic.toTopicString(): String {
        return when(this) {
            is QuizTopic.Photo -> "photo"
            is QuizTopic.Leaugue -> "leaugue"
        }
    }

}