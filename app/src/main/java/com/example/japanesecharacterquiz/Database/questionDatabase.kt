package com.example.japanesecharacterquiz.Database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [question::class], version = 1)

abstract class questionDatabase : RoomDatabase() {

    abstract fun question(): questionDao

    //companion object used to avoid opening multiple
    //instances of the database.
    companion object {
        @Volatile
        private var INSTANCE: questionDatabase? = null

        //Database is Pre-built with a .db file in assets
        fun getDatabase(context: Context): questionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    questionDatabase::class.java,
                    "questionDatabase"
                )
                    .createFromAsset("QuestionTable.db")
                    .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}