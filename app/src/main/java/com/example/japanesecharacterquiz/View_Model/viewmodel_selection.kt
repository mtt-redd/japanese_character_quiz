package com.example.japanesecharacterquiz.View_Model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import com.example.japanesecharacterquiz.Database.user
import com.example.japanesecharacterquiz.Database.user_database
import com.example.japanesecharacterquiz.Database.user_repository
import kotlin.math.log

class viewmodel_selection (application: Application)
    : AndroidViewModel(application) {

     var userlist :List<user> = emptyList()

   fun getusername() : String{

        val db = user_database.getDatabase(application)
        val userRep = user_repository(db.user())
        return userRep.getusername()
    }

    fun getscore() : Int{

        val db = user_database.getDatabase(application)
        val userRep = user_repository(db.user())

        return userRep.getscore()
    }
}