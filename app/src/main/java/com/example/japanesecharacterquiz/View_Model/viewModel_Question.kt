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

@HiltViewModel
class viewModel_question @Inject constructor(private val questionRep: questionRepository)
    : ViewModel() {


//retrive the question's Kanji based on an id
        fun retrivequestion(id : Int) : String{

            var questions = ""
            viewModelScope.launch {
            var question = questionRep.retriveQuestion(id)
                 questions = question.first().Kanji

            }
            return questions
        }

    //retrive the question's answers based on an id
    fun retriveanswers(id : Int) : List<String>{

        var answers = listOf("", "", "", "")
        viewModelScope.launch {
            var question = questionRep.retriveQuestion(id)
            answers = listOf<String>(question.first().answer1,
                question.first().answer2,
                question.first().answer3,
                question.first().answer4)
        }
        return answers
    }

    }