package com.example.japanesecharacterquiz.Database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

//The Repository is used to save data between Viewmodels

@Singleton
class user_repository @Inject constructor(private val userDao: user_dao) {

    //Save the user details so they can be reused
    public var user :List<user> = emptyList()

    //Insert user into the database
    suspend fun insertUser (user: user) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    //Deletes a user from the database
    suspend fun deleteUser () {

        val savename = user
        user = emptyList()

        withContext(Dispatchers.IO) {
            userDao.deleteUser(savename.first().username)}

        }


//Retrieve user object from the Dao
    suspend fun getUser(name: String) : List<user> {

        Log.d("Repository", "Getting User")

        return withContext(Dispatchers.IO) {
            userDao.get_user(name)
        }

    Log.d("Repository", "user got")


}

    //Set the user in the repository for next uses
    fun setuser(userlist : List<user>){

        Log.d("Reposotary", "Setting user")
        user = userlist
        Log.d("Repository user added", user.first().username)
    }

    //Get username as a String. Saved in the repository
    fun getusername(): String{
        if (!user.isEmpty()){
            Log.d("Repository", "Retrieving user")
        return user.first().username}
        else {Log.d("Repository", "No user found")
            return ""
            }
    }

    //retrive score from the Database

    fun getscore(name : String): Flow<Int>{

        return userDao.get_score(name)
    }

    //Update score when the customers get a question correctly
    suspend fun updateScore(pointAward: Int, score : Int){

        userDao.updateScore(score + pointAward, getusername())
    }


}



