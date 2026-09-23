package com.example.japanesecharacterquiz

import android.content.Context
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
object userDatabasemodule {

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): user_database {
        return user_database.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: user_database): user_dao {
        return database.user()
    }
}