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

    fun getQuestions(diff : Int): Flow<List<question>>{
        return questionDao.retriveQuestion(diff)
    }


}