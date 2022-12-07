package com.avijekrl.proald.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avijekrl.proald.data.model.QuestionDto

@Database(entities = [QuestionDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao?
}