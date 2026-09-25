package com.example.japanesecharacterquiz.View_Model

import com.example.japanesecharacterquiz.Database.user
import com.example.japanesecharacterquiz.Database.user_database
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.japanesecharacterquiz.Database.user_repository
import kotlinx.coroutines.launch
import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Singleton

@HiltViewModel
class viewmodel_user @Inject constructor(private val userRep: user_repository)
    : ViewModel() {

    /* init{
        val db = user_database.getDatabase(application)} */

    private val _navigationEvent = Channel<Unit>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    val pointReward = 50


    var username = ""


    //check if a user exists or create a new user.
    fun checkuser(name: String) {

        Log.d("", name)



        viewModelScope.launch {

            val userList = userRep.getUser(name)

            if (userList.isEmpty()) {
                adduser(name, userRep)
            }
            //Using isEmpty instead of !isEmpty because
            //it enhances readibility as the else block is
            //pretty long
            else {

                /*Double-check if the username from the database
            and the passed parameter match
            If they don't match, the user is added
            If they match, the user is found and
            user gets directed into the new
            screen*/
                if (userList.first().username != name) {

                    adduser(name, userRep)


                } else {
                    Log.d("", "User Found!")
                    // add user to repository values
                    userRep.setuser(userList)
                    Log.d("", "User has been set")
                    _navigationEvent.send(Unit)

                }
            }

        }
    }

    //this function add a user into the database
    fun adduser(name: String, userRep: user_repository) {

        Log.d("", "Adding new user")

        val user_object = user(0, name, 0)
        viewModelScope.launch {
            userRep.insertUser(user_object)

            //after adding user to database, the data is saved in the repository
            val userList: List<user> = userRep.getUser(name)
            userRep.setuser(userList)
            Log.d("", "User has been set")
            _navigationEvent.send(Unit)
        }

    }


    fun getusername(): String {

        return userRep.getusername()

    }

    fun getscore(): Int {


        return userRep.getscore()
    }

    fun deleteuser(){

        viewModelScope.launch {
        userRep.deleteUser()}
        Log.d("viewModel", "Deleting user :(")
    }

    fun updatescore(pointReward : Int){
        viewModelScope.launch {
        userRep.updateScore(pointReward)}
    }
}



