package com.example.japanesecharacterquiz.Database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

//Reposotary is used to save date between sections

class user_repository (private val userDao: user_dao) {

    //save the user details so they can be reused
    public var username :List<user> = emptyList()

    //Insert user into the database
    suspend fun insertUser (user: user) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }
//Retrive user from the Dao
    suspend fun getUser(name: String) : List<user> {

        return withContext(Dispatchers.IO) {
            userDao.get_user(name)
        }


    }

    //set the user
   fun setuser(userlist : List<user>){

        Log.d("Reposotary", "Setting user")
        username = userlist
        Log.d("Reposotary", username.first().username)
        Log.d("", username.toString())
    }

    fun getusername(): String{
        Log.d("", username.toString())
        if (!username.isEmpty()){
            Log.d("Repository", "Retrieving user")
        return username.first().username}
        else {Log.d("Repository", "No user found")
            return ""
            }
    }

    fun getscore(): Int{
        if (!username.isEmpty()){
        return username.first().score}
        else{return -1}
    }
}