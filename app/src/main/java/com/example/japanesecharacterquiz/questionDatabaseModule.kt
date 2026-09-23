package com.example.japanesecharacterquiz

import android.content.Context
import com.example.japanesecharacterquiz.Database.questionDao
import com.example.japanesecharacterquiz.Database.questionDatabase
import com.example.japanesecharacterquiz.Database.user_dao
import com.example.japanesecharacterquiz.Database.user_database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object questionDatabaseModule {

    @Provides
    @Singleton
    fun provideQuestionDatabase(@ApplicationContext context: Context): questionDatabase {
        return questionDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideQuestionDao(database: questionDatabase): questionDao {
        return database.question()
    }
}