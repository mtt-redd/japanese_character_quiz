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
import androidx.lifecycle.viewModelScope
import com.example.japanesecharacterquiz.Database.questionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Singleton
import com.example.japanesecharacterquiz.Database.question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class viewModel_question @Inject constructor(private val questionRep: questionRepository)
    : ViewModel() {



    private val _showDialog = MutableStateFlow(false)
    private val _showWrongDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()
    val showWrongDialog: StateFlow<Boolean> = _showWrongDialog.asStateFlow()



    //Kotlin doesn't allow null values. So this is a default answer is case
    //something goes wrong
val defaultquestion = question(0, "Loading",
    "Loading","Loading", "Loading",
    "Loading", "Loading", "Loading",
    "Loading", "Loading", 1, "Loading",
    "Loading", 1)

    val questions: StateFlow<question> = questionRep.
    getQuestions(getDifficulty()).map { questions ->
        questions.random() //sends a random question instead of a specific one
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.
            WhileSubscribed(5000),
            initialValue = defaultquestion
        )

    fun enableHira(){
            questionRep.enableHira()
            Log.d("Viewpoint", "Enable Hira")
        }
    fun disableHira(){
        questionRep.disableHira()
    }

    fun setdifficulty(diff: String){


        questionRep.setdifficulty(diff)
    }

    fun getHira() : Boolean{

        return questionRep.getHira()
    }

    fun getDifficulty() : Int{

        return questionRep.getDifficulty()

    }


    fun setSelectedAnswer(select : Int){


        questionRep.setSelectedAnswer (select)
        Log.d("Repository", "Answer Set")
        Log.d("Repository", select.toString())

    }

    fun verifyanswer() {

        val getcorrectanswer = questions.value.correctanswer

        Log.d("", questionRep.selectAnswer.toString())
        Log.d("", questionRep.selectAnswer.toString())

        if (questionRep.selectAnswer == getcorrectanswer) {

            Log.d("Viewmodel", "You got it right!")

            openDialog()

        }
        else {
            Log.d("", questions.value.wrong)
            openWrongDialog()
        }

    }

    fun openDialog() {
        _showDialog.value = true
    }

    fun onDialogDismissed() {
        _showDialog.value = false
    }

    fun openWrongDialog() {
        _showWrongDialog.value = true
    }

    fun onWrongDialogDismissed() {
        _showWrongDialog.value = false
    }

    fun loadNewQuestion(){

        val questions: StateFlow<question> = questionRep.
        getQuestions(getDifficulty()).map { questions ->
            questions.random() //sends a random question instead of a specific one
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.
                WhileSubscribed(5000),
                initialValue = defaultquestion
            )
    }




    }








