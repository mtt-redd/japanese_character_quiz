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

    //save the user details so they can be reused
    public var question :List<question> = emptyList()

    suspend fun insertQuestion (question: question) {
        withContext(Dispatchers.IO) {
            questionDao.insert(question)
        }
    }

    suspend fun retriveQuestion(id : Int) : List<question>{
        return withContext(Dispatchers.IO){
            questionDao.retriveQuestion(id)
        }
    }

    suspend fun maximumQuestionNumber(){
        withContext(Dispatchers.IO){
            questionDao.maxnumber()
        }
    }

     fun setquestion(questionList : List<question>){

        Log.d("Reposotary1", "Setting question")
        question = questionList
        Log.d("Reposotary2", question.first().Kanji)
    }

    fun retriveAnswerValues() : List<String>{

        return listOf(question.first().answer1,
            question.first().answer2,
            question.first().answer3,
            question.first().answer4,)
    }

    fun retriveQuestionValues(): String{

        return question.first().Kanji
    }

}