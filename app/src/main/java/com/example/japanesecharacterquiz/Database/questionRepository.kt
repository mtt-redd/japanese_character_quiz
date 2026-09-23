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
class questionRepository @Inject constructor(private val questionDao: questionDao) {

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

}