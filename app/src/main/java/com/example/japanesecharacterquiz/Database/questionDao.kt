package com.example.japanesecharacterquiz.Database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao

interface questionDao {

    /* @Query("SELECT * FROM user")
     suspend fun loadAllUsers(): List<user> */

    //OnConflictStrategy.Ignore to avoid duplicate users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: question)

    @Query("SELECT * FROM QuestionTable WHERE id = :id")
    suspend fun retriveQuestion(id: Int) : List<question>

    @Query("SELECT COUNT(*) FROM QuestionTable")
    suspend fun maxnumber() : Int


}