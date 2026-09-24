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

//Reposotary is used to save data between functions / viewmodels

@Singleton
class user_repository @Inject constructor(private val userDao: user_dao) {

    //save the user details so they can be reused
    public var user :List<user> = emptyList()

    //Insert user into the database
    suspend fun insertUser (user: user) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    suspend fun deleteUser () {
        withContext(Dispatchers.IO) {
            userDao.deleteUser(user.first().username)
        }
    }

//Retrieve user from the Dao
    suspend fun getUser(name: String) : List<user> {

        Log.d("Repository", "Getting User")

        return withContext(Dispatchers.IO) {
            userDao.get_user(name)
        }

    Log.d("Repository", "user got")


}

    //set the user
    fun setuser(userlist : List<user>){

        Log.d("Reposotary", "Setting user")
        user = userlist
        Log.d("Repository user added", user.first().username)
    }

    fun getusername(): String{
        if (!user.isEmpty()){
            Log.d("Repository", "Retrieving user")
        return user.first().username}
        else {Log.d("Repository", "No user found")
            return ""
            }
    }

    fun getscore(): Int{
        if (!user.isEmpty()){
        return user.first().score}
        else{return -1}
    }
}