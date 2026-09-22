package com.example.japanesecharacterquiz.Database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [user::class], version = 1)

abstract class user_database : RoomDatabase() {

    abstract fun user(): user_dao

    //companion object used to avoid opening multiple
    //instances of the database.
    companion object {
        @Volatile
        private var INSTANCE: user_database? = null

        fun getDatabase(context: Context): user_database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    user_database::class.java,
                    "User_Table"
                )
                    .setJournalMode(JournalMode.WRITE_AHEAD_LOGGING)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}