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
                navController.navigate("selection"){
                    //blocking back after the login
                popUpTo("login") { inclusive = true }}
            })
        }

        composable("selection") {
            Selection(onNavigateToLogin = {
                navController.navigate("login"){
                    //blocking back after the login
                    popUpTo("selection") { inclusive = true }}
            })
        }
    }
}