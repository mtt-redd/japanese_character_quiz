package com.example.japanesecharacterquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.japanesecharacterquiz.ui.theme.JapaneseCharacterQuizTheme
import com.example.japanesecharacterquiz.View.Greeting
import com.example.japanesecharacterquiz.View.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

