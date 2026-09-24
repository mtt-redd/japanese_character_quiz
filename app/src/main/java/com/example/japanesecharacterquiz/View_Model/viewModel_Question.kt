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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class viewModel_question @Inject constructor(private val questionRep: questionRepository)
    : ViewModel() {



    val questions: StateFlow<List<question>> = questionRep.getQuestions(1)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // Keeps flow active for 5s after UI unbinds (handles config changes)
            initialValue = emptyList()
        )

}



