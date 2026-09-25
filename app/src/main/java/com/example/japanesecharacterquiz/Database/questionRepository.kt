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
import kotlin.collections.first

//Reposotary is used to save data between functions / viewmodels

@Singleton
class questionRepository @Inject constructor(private val questionDao: questionDao) {

    var useHira = false
    var questiondifficulty = 1

    var selectAnswer = 1

    fun getQuestions(diff : Int): Flow<List<question>>{
        return questionDao.retriveQuestion(diff)
    }

    fun enableHira(){
        useHira = true
        Log.d("Repository", "Enable Hira")
    }
    fun disableHira(){
        useHira = false
    }

    fun setdifficulty(diff: String){

        when (diff) {
            "Easy" -> questiondifficulty = 1
            "Normal" -> questiondifficulty = 2
            "Hard" -> questiondifficulty = 3
            else -> questiondifficulty = 1
        }
    }

    fun getHira() : Boolean{

        return useHira
    }

    fun getDifficulty() : Int{

        Log.d("Repository", questiondifficulty.toString())
        return questiondifficulty

    }

    fun setSelectedAnswer(select : Int){

        selectAnswer = select + 1 //to compensate for index

    }


}