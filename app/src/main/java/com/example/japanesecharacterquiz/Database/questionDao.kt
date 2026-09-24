package com.example.japanesecharacterquiz.Database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao

interface questionDao {

    /* @Query("SELECT * FROM user")
     suspend fun loadAllUsers(): List<user> */

    //OnConflictStrategy.Ignore to avoid duplicate users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: question)

    @Query("SELECT * FROM QuestionTable WHERE difficulty = :diff")
    fun retriveQuestion(diff: Int) : Flow<List<question>>



}