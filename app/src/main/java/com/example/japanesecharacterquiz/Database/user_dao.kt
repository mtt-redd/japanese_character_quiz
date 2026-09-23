package com.example.japanesecharacterquiz.Database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao

interface user_dao {

   /* @Query("SELECT * FROM user")
    suspend fun loadAllUsers(): List<user> */

    //OnConflictStrategy.Ignore to avoid duplicate users
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: user)

    @Query("SELECT * FROM User_Table WHERE username = :name")
    fun get_user(name: String): List<user>

    @Query("DELETE FROM User_Table WHERE username = :name")
    fun deleteUser(name: String)


}