package com.example.japanesecharacterquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.japanesecharacterquiz.ui.theme.JapaneseCharacterQuizTheme
import com.example.japanesecharacterquiz.View.Greeting
import com.example.japanesecharacterquiz.View.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //This is necessary to start Hilt.
class MainActivity : ComponentActivity() {

    //This is the start of the application.
    //It calls the MainNavigation function that
    //Run the different screens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseCharacterQuizTheme {
                MainNavigation()

                }
            }
        }
    }

