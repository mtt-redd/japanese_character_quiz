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
        // Indicates the first screen user will see when
        //MainActivity starts. It's the login screen
        startDestination = "login"
    ) {
        //Allow to switch from Login to Selection. Used when a user is created or found.
        composable("login") {
            Greeting(onNavigateToSelection = {
                navController.navigate("selection") {
                    //Used to remove Login from the backstack
                    popUpTo("login") { inclusive = true }
                }
            })
        }

//Allow to switch from Selection to Login. Used when login off or an account is deleted.

        composable("selection") {
            Selection(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("selection") { inclusive = true }
                    }
                },
                //Allow from Selection to Quiz. This is main quiz part.
                onNavigateToQuiz = {
                    navController.navigate("quiz") {
                        popUpTo("selection") { inclusive = true }
                    }
                }
            )
        }
        //Allow to switch from Quiz to Selection. Used when the user exit the quiz
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