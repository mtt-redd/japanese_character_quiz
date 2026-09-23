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
import com.example.japanesecharacterquiz.Database.questionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Singleton
import com.example.japanesecharacterquiz.Database.question
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class viewModel_question @Inject constructor(private val questionRep: questionRepository)
    : ViewModel() {

    suspend fun setquestion(id: Int){


            val questionList = questionRep.retriveQuestion(id)
            Log.d("Checking if repository is ready", "If")

            if (!questionList.isEmpty()) {

                Log.d("Viewmodel1", questionList.first().Kanji)
                questionRep.setquestion(questionList)
            }
            else {
            Log.d("Viewmodel 2", "List is empty")}



    }

//retrive the question's Kanji based on an id
      suspend fun retrivequestion() : String {

        return questionRep.retriveQuestionValues()
        }


    //retrive the question's answers based on an id
   suspend fun retriveanswers() : List<String>{

        return questionRep.retriveAnswerValues()
    }

    }
