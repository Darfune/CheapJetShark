package com.example.cheapjetshark.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cheapjetshark.navigation.root.NavigationGraph
import com.example.cheapjetshark.screens.auth.LogInScreen
import com.example.cheapjetshark.screens.auth.RegistrationScreen
 import com.example.cheapjetshark.screens.auth.ResetPasswordScreen

enum class AuthScreens {
    LogInScreen,
    RegistrationScreen,
    ResetPasswordScreen
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationGraph.AUTH,
        startDestination = AuthScreens.LogInScreen.name
    ) {
        composable(AuthScreens.LogInScreen.name){
            LogInScreen(navController = navController)
        }
        composable(AuthScreens.RegistrationScreen.name){
            RegistrationScreen(navController = navController)
        }
        composable(AuthScreens.ResetPasswordScreen.name){
            ResetPasswordScreen(navController = navController)
        }


    }
}