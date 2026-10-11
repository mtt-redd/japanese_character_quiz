package com.example.japanesecharacterquiz.Database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
//DAO Stores all the queries of the table User_Table
interface user_dao {

    //Insert an user. To insert it it needs the following:
    //Username: String
    //Score : Int
    //OnConflictStrategy.Ignore to avoid duplicate users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: user)

    //Retrieve all columns based on the username
    @Query("SELECT * FROM User_Table WHERE username = :name")
    fun get_user(name: String): List<user>

    //Retrieve score based on the username
    @Query("SELECT score FROM User_Table WHERE username = :name")
    fun get_score(name: String): Flow<Int>

    //Deletes the user. Uses the name to find the right row.
    @Query("DELETE FROM User_Table WHERE username = :name")
    fun deleteUser(name: String)

    //Updates the score when user select the correct answer.
    @Query("UPDATE User_Table SET score = :newScore WHERE username = :name")
    suspend fun updateScore(newScore: Int, name: String)


}