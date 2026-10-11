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

    //Saves if customer wants to use Hiragana
    var useHira = false
    //Saves customer's difficulty
    var questiondifficulty = 1

    //Saves the Answer Selected by the customer
    var selectAnswer = 1

    //Saves one previously gotten wrong question
    var prevWrong = -1

    //Get questions from the database
    fun getQuestions(diff : Int): Flow<List<question>>{
        return questionDao.retriveQuestion(diff)
    }

    //Retrive specifically a previously gotten wrong question
    fun retriveWrongQuestion(id : Int) : Flow<List<question>>{

        return questionDao.retriveWrongQuestion(id)
    }

    //Change the useHira variable to true
    fun enableHira(){
        useHira = true
        Log.d("Repository", "Enable Hira")
    }
    //Change the useHira variable to false
    fun disableHira(){
        useHira = false
    }

    //Change the questionDifficulty variable to the selected difficulty
    fun setdifficulty(diff: String){

        //a switch to convert the String to Int
        when (diff) {
            "Easy" -> questiondifficulty = 1
            "Normal" -> questiondifficulty = 2
            "Hard" -> questiondifficulty = 3
            else -> questiondifficulty = 1
        }
    }

    //Return user's choice on Hiragana

    fun getHira() : Boolean{

        return useHira
    }

    //Return user's selected difficulty
    fun getDifficulty() : Int{

        Log.d("Repository", questiondifficulty.toString())
        return questiondifficulty

    }

    //Set the answer selected by the user.
    fun setSelectedAnswer(select : Int){

        selectAnswer = select + 1 //to compensate for index

    }

    //Saves a question a user got wrong. @Param id of the wrong question
    fun setWrongQuestion (id : Int){

        prevWrong = id

        Log.d("Rep - SetWrongQuestion", id.toString())

    }

    //Returns a question a user got wrong
    fun getWrongQuestion () : Int{

        return prevWrong

    }







}