package com.example.japanesecharacterquiz.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Greeting(onNavigateToSelection = {
                navController.navigate("selection") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        // Combine both callbacks into a single "selection" destination
        composable("selection") {
            Selection(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("selection") { inclusive = true }
                    }
                },
                onNavigateToQuiz = {
                    navController.navigate("quiz") {
                        popUpTo("selection") { inclusive = true }
                    }
                }
            )
        }

        // Add the missing "quiz" route destination
        composable("quiz") {
            Question(
                onNavigateToSelection = {
                    navController.navigate("selection") {
                        popUpTo("quiz") { inclusive = true }
                    }
                }
            )
        }
    }
}